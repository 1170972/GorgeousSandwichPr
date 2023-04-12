package pt.isep.arqsoft.GorgeousSandwich.dto.order;

import java.util.Set;

public class OrderDTO {

    public Long orderId;
    
    public String orderStatus;
    
    public DeliveryTimeDTO deliveryTime;
    
    public String deliveryDate;
    
    public String orderDate;
    
    public Set<OrderItemDTO> orderItems;

    public String email;

    public OrderDTO(){

    }

    public OrderDTO(String orderStatus, DeliveryTimeDTO deliveryTime,
                    String deliveryDate, String orderDate, Set<OrderItemDTO> orderItems,String email) {
        this.orderStatus = orderStatus;
        this.deliveryTime = deliveryTime;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.email = email;
    }

    public OrderDTO(Long orderId, String orderStatus, DeliveryTimeDTO deliveryTime,
                    String deliveryDate, String orderDate, Set<OrderItemDTO> orderItems,String email) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.deliveryTime = deliveryTime;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.email = email;
    }
}
