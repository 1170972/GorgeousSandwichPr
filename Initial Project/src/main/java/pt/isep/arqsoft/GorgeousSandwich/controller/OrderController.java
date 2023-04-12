package pt.isep.arqsoft.GorgeousSandwich.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.Order;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.OrderConverter;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.OrderDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.OrderItemDTO;
import pt.isep.arqsoft.GorgeousSandwich.exceptions.InvalidOperationException;
import pt.isep.arqsoft.GorgeousSandwich.exceptions.ResourceNotFoundException;
import pt.isep.arqsoft.GorgeousSandwich.repository.order.wrapper.OrderRepositoryWrapperJPA;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.wrapper.SandwichRepositoryWrapperJPA;

import static java.time.temporal.ChronoUnit.DAYS;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/gorgeous-sandwich")
public class OrderController {

    private OrderRepositoryWrapperJPA orderRepository;
    
    private SandwichRepositoryWrapperJPA sandwichRepository;
    
    private OrderConverter orderConverter;

    public OrderController(OrderRepositoryWrapperJPA orderRepository, SandwichRepositoryWrapperJPA sandwichRepository, OrderConverter orderConverter){
    	this.orderRepository = orderRepository;
    	this.sandwichRepository = sandwichRepository;
        this.orderConverter = orderConverter;
    }

    @GetMapping("/orders")
    public List<OrderDTO> listAll() {
        List<Order> orders = orderRepository.findAll();
        return orderConverter.convertListToDTO(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException{
        try {
            Order order = orderRepository.getById(orderId);
            return ResponseEntity.ok().body(orderConverter.convertToDTO(order));
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Sandwich not found with id " + orderId);
        }
    }

    @GetMapping("/orders/email/{id}")
    public List<OrderDTO> getByEmail(@PathVariable(value = "id") String email) throws ResourceNotFoundException{
            List<Order> orders = orderRepository.getByEmail(email);
            return orderConverter.convertListToDTO(orders);
    }

    @GetMapping("/orders/times")
    public List<DeliveryTimeDTO> getDeliveryTimes(){
        return DeliveryTime.calculateIntervals();
    }

    @PostMapping("/orders")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
        checkIfSandwichExists(orderDTO.orderItems);
        orderDTO.orderStatus="Created";
        orderDTO.orderDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Order order = orderConverter.convertFromDTO(orderDTO);
        order = orderRepository.save(order);
        return orderConverter.convertToDTO(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(value = "id") Long orderId,@RequestBody OrderDTO orderDTO) throws ResourceNotFoundException, InvalidOperationException {
        try{
            Order order = orderRepository.getById(orderId);
            if(LocalDate.now().until(order.obtainDeliveryDate().obtainDate(),DAYS) < 5){
                throw new InvalidOperationException("Cant update order. Less of five days remaining to delivery");
            }
            order.changeDeliveryTime(orderDTO.deliveryTime.startTime,orderDTO.deliveryTime.endTime);
            order.changeOrderItems(orderConverter.convertOrderItemsListFromDTO(orderDTO.orderItems));
            order = this.orderRepository.update(order);
            return ResponseEntity.ok().body(this.orderConverter.convertToDTO(order));
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Order not found with id"+orderId);
        }
    }

    private void checkIfSandwichExists(Set<OrderItemDTO> items){
        for(OrderItemDTO item : items){
            if(!sandwichRepository.checkIfExists(item.sandwichId)){
                throw new IllegalArgumentException("Sandwich with id " + item.sandwichId + " not found");
            }
        }
    }
}
