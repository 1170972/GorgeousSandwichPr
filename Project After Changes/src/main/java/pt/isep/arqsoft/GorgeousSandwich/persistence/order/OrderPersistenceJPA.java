package pt.isep.arqsoft.GorgeousSandwich.persistence.order;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Entity
@Table(name = "ORDERS")
public class OrderPersistenceJPA {

    @Column(name = "orderStatus", nullable = false)
    private String orderStatus;
    
    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;
    
    @Column(name = "endTime", nullable = false)
    private LocalTime endTime;
    
    @Column(name = "deliveryDate", nullable = false)
    private LocalDate deliveryDate;
    
    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_item", joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "order_id")})
    @MapKeyColumn(name = "sandwichId")
    @Column(name = "quantity", nullable = false)
    private Map<Long,Integer> orderItems;

    @Column(name = "email", nullable = false)
    private String email;

    public OrderPersistenceJPA(String orderStatus, LocalTime startTime, LocalTime endTime, LocalDate deliveryDate, LocalDate orderDate, Map<Long, Integer> orderItems,String email) {
        this.orderStatus = orderStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.email = email;
    }

    public OrderPersistenceJPA(String orderStatus, LocalTime startTime, LocalTime endTime, LocalDate deliveryDate, LocalDate orderDate, Long orderId, Map<Long, Integer> orderItems,String email) {
        this.orderStatus = orderStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.email = email;
    }

    public OrderPersistenceJPA() {
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Map<Long, Integer> getOrderItems() {
        return orderItems;
    }

    public String getEmail() {
        return email;
    }
}
