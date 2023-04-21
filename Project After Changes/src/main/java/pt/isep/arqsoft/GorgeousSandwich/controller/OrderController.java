package pt.isep.arqsoft.GorgeousSandwich.controller;

import java.util.TreeSet;
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

    public OrderController(OrderRepositoryWrapperJPA orderRepository, SandwichRepositoryWrapperJPA sandwichRepository){
    	this.orderRepository = orderRepository;
    	this.sandwichRepository = sandwichRepository;
    }

    @GetMapping("/orders")
    public List<OrderDTO> listAll() {
        return OrderConverter.convertListToDTO(orderRepository.findAll());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException{
        try {
            return ResponseEntity.ok().body(OrderConverter.convertToDTO(orderRepository.getById(orderId)));
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Sandwich not found with id " + orderId);
        }
    }

    @GetMapping("/orders/email/{id}")
    public List<OrderDTO> getByEmail(@PathVariable(value = "id") String email) {
        return OrderConverter.convertListToDTO(orderRepository.getByEmail(email));
    }

    @GetMapping("/orders/times")
    public static List<DeliveryTimeDTO> getDeliveryTimes(){
        return DeliveryTime.obtainPossibleIntervals();
    }

    @PostMapping("/orders")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
        checkIfSandwichExists(orderDTO.orderItems);
        orderDTO.orderStatus = "Created";
        orderDTO.orderDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return OrderConverter.convertToDTO(orderRepository.save(OrderConverter.convertFromDTO(orderDTO)));
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(value = "id") Long orderId,@RequestBody OrderDTO orderDTO) throws ResourceNotFoundException, InvalidOperationException {
        try{
            Order order = orderRepository.getById(orderId);
            if(LocalDate.now().until(order.obtainDeliveryDate().obtainDate(),DAYS) < 5){
                throw new InvalidOperationException("Cant update order. Less of five days remaining to delivery");
            }
            DeliveryTimeDTO deliveryTimeDTO = orderDTO.deliveryTime;
            order.changeDeliveryTime(deliveryTimeDTO.startTime, deliveryTimeDTO.endTime);
            order.changeOrderItems(OrderConverter.convertOrderItemsListFromDTO(orderDTO.orderItems));
            return ResponseEntity.ok().body(OrderConverter.convertToDTO(this.orderRepository.update(order)));
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Order not found with id"+orderId);
        }
    }

    private void checkIfSandwichExists(Set<OrderItemDTO> items){
        Set<Long> sandwichIds = new TreeSet<>();
        for(OrderItemDTO item : items){
            sandwichIds.add(item.sandwichId);
        }
        if(sandwichRepository.findByIds(sandwichIds).size() != sandwichIds.size()){
            throw new IllegalArgumentException("One or more Sandwiches do not exist");
        }
    }
}
