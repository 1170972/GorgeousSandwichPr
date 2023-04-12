package pt.isep.arqsoft.GorgeousSandwich.dto.order;

import pt.isep.arqsoft.GorgeousSandwich.domain.order.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;

@Service
public class OrderConverter {

    public OrderDTO convertToDTO(Order order){
        if(order.obtainOrderId() == null){
            return new OrderDTO(order.obtainOrderStatus().obtainName(),new DeliveryTimeDTO(order.obtainDeliveryTime().obtainStartTime().toString(),
                    order.obtainDeliveryTime().obtainEndTime().toString())
                    , order.obtainDeliveryDate().toString(),
                    order.obtainOrderDate().toString(), convertOrderItemsListToDTO(order.obtainOrderItems()),order.obtainUserEmail().obtainName());
        }
        return new OrderDTO(order.obtainOrderId().obtainID(), order.obtainOrderStatus().obtainName(),
                new DeliveryTimeDTO(order.obtainDeliveryTime().obtainStartTime().toString(),
                order.obtainDeliveryTime().obtainEndTime().toString()), order.obtainDeliveryDate().toString(),
                order.obtainOrderDate().toString(), convertOrderItemsListToDTO(order.obtainOrderItems()),order.obtainUserEmail().obtainName());
    }

    public Order convertFromDTO(OrderDTO orderDTO){
        if(orderDTO.orderId==null){
            return new Order(OrderStatus.valueOf(orderDTO.orderStatus),
                    DeliveryTime.valueOf(LocalTime.parse(orderDTO.deliveryTime.startTime), LocalTime.parse(orderDTO.deliveryTime.endTime)),
                    DeliveryDate.valueOf(LocalDate.parse(orderDTO.deliveryDate)),
                    OrderDate.valueOf(LocalDate.parse(orderDTO.orderDate)), convertOrderItemsListFromDTO(orderDTO.orderItems), UserEmail.valueOf(orderDTO.email));
        }
        return new Order(OrderStatus.valueOf(orderDTO.orderStatus),
                DeliveryTime.valueOf(LocalTime.parse(orderDTO.deliveryTime.startTime), LocalTime.parse(orderDTO.deliveryTime.endTime)),
                DeliveryDate.valueOf(LocalDate.parse(orderDTO.deliveryDate)),
                OrderDate.valueOf(LocalDate.parse(orderDTO.orderDate)),OrderID.valueOf(orderDTO.orderId), convertOrderItemsListFromDTO(orderDTO.orderItems),UserEmail.valueOf(orderDTO.email));
    }

    public OrderItemDTO convertOrderItemToDTO(OrderItem orderItem){
        return new OrderItemDTO(orderItem.obtainSandwichId().obtainID(), orderItem.obtainQuantity().obtainUnits());
    }

    public OrderItem convertOrderItemFromDTO(OrderItemDTO orderItemDTO){
        return OrderItem.valueOf(SandwichID.valueOf(orderItemDTO.sandwichId), Quantity.valueOf(orderItemDTO.quantity));
    }

    public Set<OrderItemDTO> convertOrderItemsListToDTO(Set<OrderItem> orderItems){
        Set<OrderItemDTO> orderItemsDTO = new HashSet<>();
        for (OrderItem o : orderItems){
            orderItemsDTO.add(convertOrderItemToDTO(o));
        }
        return orderItemsDTO;
    }

    public Set<OrderItem> convertOrderItemsListFromDTO(Set<OrderItemDTO> orderItemsDTO){
        Set<OrderItem> orderItems = new HashSet<>();
        for (OrderItemDTO o : orderItemsDTO){
            orderItems.add(convertOrderItemFromDTO(o));
        }
        return orderItems;
    }

    public List<OrderDTO> convertListToDTO(List<Order> orderList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order o : orderList){
            orderDTOList.add(convertToDTO(o));
        }
        return orderDTOList;
    }

}
