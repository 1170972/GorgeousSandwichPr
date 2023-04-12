package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.apache.commons.lang.Validate;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IEntity;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;

public class Order implements IEntity<Order> {
	
	private OrderStatus orderStatus;
	
	private DeliveryTime deliveryTime;
	
	private DeliveryDate deliveryDate;
	
	private OrderDate orderDate;
	
	private OrderID orderId;
	
	private Set<OrderItem> orderItems;

	private UserEmail email;
	
	private static final int DELIVERY_BEGINNING = 2;
	
	private static final int DELIVERY_ENDING = 29;
	
	public Order(OrderStatus orderStatus, DeliveryTime deliveryTime, DeliveryDate deliveryDate, OrderDate orderDate, Set<OrderItem> orderItems,UserEmail email) {
		Validate.noNullElements(new Object [] {orderStatus, deliveryTime, deliveryDate, orderDate, orderItems,email}, "Order status, delivery time, delivery date, order date and order items cannot be null.");
		Validate.notEmpty(orderItems, "List of OrderItem must not be empty.");
		Validate.isTrue(deliveryDate.obtainDate().isAfter(orderDate.obtainDate().plusDays(DELIVERY_BEGINNING)) && deliveryDate.obtainDate().isBefore(orderDate.obtainDate().plusDays(DELIVERY_ENDING)),"Delivery date must be up to 3 days after or 28 days after the order date");
		this.orderStatus = orderStatus;
		this.deliveryTime = deliveryTime;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
		this.email = email;
	}

	public Order(OrderStatus orderStatus, DeliveryTime deliveryTime, DeliveryDate deliveryDate, OrderDate orderDate, OrderID orderId, Set<OrderItem> orderItems, UserEmail email) {
		Validate.noNullElements(new Object [] {orderStatus, deliveryTime, deliveryDate, orderDate,orderId, orderItems,email}, "Order status, delivery time, delivery date, order date, orderId and order items cannot be null.");
		Validate.notEmpty(orderItems, "List of OrderItem must not be empty.");
		Validate.isTrue(deliveryDate.obtainDate().isAfter(orderDate.obtainDate().plusDays(2)) && deliveryDate.obtainDate().isBefore(orderDate.obtainDate().plusDays(29)),"Delivery date must be up to 3 days after or 28 days after the order date");
		this.orderStatus = orderStatus;
		this.deliveryTime = deliveryTime;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.orderId = orderId;
		this.orderItems = orderItems;
		this.email = email;
	}

	public OrderStatus obtainOrderStatus() {
		return this.orderStatus;
	}
	
	public DeliveryTime obtainDeliveryTime() {
		return this.deliveryTime;
	}
	
	public DeliveryDate obtainDeliveryDate() {
		return this.deliveryDate;
	}
	
	public OrderDate obtainOrderDate() {
		return this.orderDate;
	}
	
	public Set<OrderItem> obtainOrderItems() {
		return this.orderItems;
	}

	public UserEmail obtainUserEmail(){return this.email;}
	
	public OrderID obtainOrderId() {
		return this.orderId;
	}

	public void changeOrderStatus(String name){
		this.orderStatus = orderStatus.changeStatus(name);
	}

	public void changeDeliveryTime(String startTime,String endTime){
		LocalTime sTime = LocalTime.parse(startTime);
		LocalTime eTime = LocalTime.parse(endTime);
		this.deliveryTime = deliveryTime.changeDeliveryTime(sTime,eTime);
	}

	public void changeDeliveryDate(String date){
		LocalDate d = LocalDate.parse(date);
		Validate.isTrue(d.isAfter(orderDate.obtainDate().plusDays(2)) && d.isBefore(orderDate.obtainDate().plusDays(29)),"Delivery date must be up to 3 days after or 28 days after the order date");
		this.deliveryDate = deliveryDate.changeDate(d);
	}

	public void changeOrderItems(Set<OrderItem>orderItems){
		this.orderItems = orderItems;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Order other = (Order) o;

	    return sameIdentityAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.orderId.hashCode();
	}

	@Override
	public boolean sameIdentityAs(Order other) {
		return other != null && orderId.sameValueAs(other.orderId);
	}
	
	@Override
	public String toString() {
		return this.orderId.toString();
	}

}
