package pt.isep.arqsoft.GorgeousSandwich.repository.order.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.order.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;
import pt.isep.arqsoft.GorgeousSandwich.persistence.order.OrderPersistenceJPA;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class OrderMapperJPA {

	public static Order convertToDomain(OrderPersistenceJPA persistence) {
		Set<OrderItem> set = new TreeSet<>();
		for (Long l : persistence.getOrderItems().keySet()) {
			OrderItem orderItem = OrderItem.valueOf(SandwichID.valueOf(l), Quantity.valueOf(persistence.getOrderItems().get(l)));
			set.add(orderItem);
		}
		if(persistence.getOrderId() == null){
			return new Order(OrderStatus.valueOf(persistence.getOrderStatus()), DeliveryTime.valueOf(persistence.getStartTime(), persistence.getEndTime()), DeliveryDate.valueOf(persistence.getDeliveryDate()), OrderDate.valueOf(persistence.getOrderDate()), set, UserEmail.valueOf(persistence.getEmail()));
		}
		return new Order(OrderStatus.valueOf(persistence.getOrderStatus()), DeliveryTime.valueOf(persistence.getStartTime(), persistence.getEndTime()), DeliveryDate.valueOf(persistence.getDeliveryDate()), OrderDate.valueOf(persistence.getOrderDate()), OrderID.valueOf(persistence.getOrderId()), set, UserEmail.valueOf(persistence.getEmail()));
	}

	public static OrderPersistenceJPA convertToPersistence(Order domain) {
		Map<Long, Integer> map = new Hashtable<>();
		for(OrderItem orderItem : domain.obtainOrderItems()){
			map.put(orderItem.obtainSandwichId().obtainID(), orderItem.obtainQuantity().obtainUnits());
		}
		DeliveryTime deliveryTime = domain.obtainDeliveryTime();
		if(domain.obtainOrderId() == null){
			return new OrderPersistenceJPA(domain.obtainOrderStatus().obtainName(), deliveryTime.obtainStartTime(), deliveryTime.obtainEndTime(), domain.obtainDeliveryDate().obtainDate(), domain.obtainOrderDate().obtainDate(), map,domain.obtainUserEmail().obtainName());
		}
		return new OrderPersistenceJPA(domain.obtainOrderStatus().obtainName(), deliveryTime.obtainStartTime(), deliveryTime.obtainEndTime(), domain.obtainDeliveryDate().obtainDate(), domain.obtainOrderDate().obtainDate(), domain.obtainOrderId().obtainID(), map,domain.obtainUserEmail().obtainName());
	}

	public static List<Order> convertListToDomain(List<OrderPersistenceJPA> persistenceList) {
		List<Order> orders = new ArrayList<>();
		for (OrderPersistenceJPA o : persistenceList) {
			orders.add(convertToDomain(o));
		}
		return orders;
	}

}
