package pt.isep.arqsoft.GorgeousSandwich.repository.order.wrapper;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang.Validate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.Order;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;
import pt.isep.arqsoft.GorgeousSandwich.exceptions.InvalidOperationException;
import pt.isep.arqsoft.GorgeousSandwich.persistence.order.OrderPersistenceJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.order.IOrderRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.order.mapper.OrderMapperJPA;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HALF_DAYS;

@Service("OrderRepositoryWrapperJPA")
public class OrderRepositoryWrapperJPA {

	private OrderMapperJPA mapper;

	private IOrderRepositoryJPA repository;
	
	public OrderRepositoryWrapperJPA(OrderMapperJPA mapper, IOrderRepositoryJPA repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	public Order save(Order model) {
		this.validateModel(model);
		OrderPersistenceJPA orderJPA = this.mapper.convertToPersistence(model);
		return this.mapper.convertToDomain(this.repository.save(orderJPA));
	}

	public List<Order> findAll() {
		List<OrderPersistenceJPA> ordersJPA = this.repository.findAll();
		return this.mapper.convertListToDomain(ordersJPA);
	}

	public Order update(Order model) throws InvalidOperationException {
		if(this.repository.existsById(model.obtainOrderId().obtainID())){
			this.validateModel(model);
			OrderPersistenceJPA order = this.mapper.convertToPersistence(model);
			return this.mapper.convertToDomain(this.repository.save(order));
		}else {
			throw  new NoSuchElementException();
		}
	}

	public Order getById(Long Id) {
		return this.mapper.convertToDomain(this.repository.findById(Id).get());
	}

	public List<Order> getByEmail(String email) {
		return this.mapper.convertListToDomain(this.repository.findByEmail(email));
	}

	private void validateModel(Order model) {
		List<DeliveryTimeDTO> list = DeliveryTime.calculateIntervals();
		DeliveryTimeDTO time = new DeliveryTimeDTO(model.obtainDeliveryTime().obtainStartTime().toString(),model.obtainDeliveryTime().obtainEndTime().toString());
		Validate.isTrue(list.contains(time),"Invalid delivery time interval");
	}

}
