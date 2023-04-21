package pt.isep.arqsoft.GorgeousSandwich.repository.order.wrapper;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.Order;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;
import pt.isep.arqsoft.GorgeousSandwich.repository.order.IOrderRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.order.mapper.OrderMapperJPA;

@Service("OrderRepositoryWrapperJPA")
public class OrderRepositoryWrapperJPA {

	private IOrderRepositoryJPA repository;
	
	public OrderRepositoryWrapperJPA(IOrderRepositoryJPA repository) {
		this.repository = repository;
	}

	public Order save(Order model) {
		return OrderMapperJPA.convertToDomain(this.repository.save(OrderMapperJPA.convertToPersistence(model)));
	}

	public List<Order> findAll() {
		return OrderMapperJPA.convertListToDomain(this.repository.findAll());
	}

	public Order update(Order model) {
		return OrderMapperJPA.convertToDomain(this.repository.save(OrderMapperJPA.convertToPersistence(model)));
	}

	public Order getById(Long Id) {
		return OrderMapperJPA.convertToDomain(this.repository.findById(Id).get());
	}

	public List<Order> getByEmail(String email) {
		return OrderMapperJPA.convertListToDomain(this.repository.findByEmail(email));
	}

}
