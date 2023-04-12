package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class OrderStatus implements IValueObject<OrderStatus> {
	
	private String name;
	
	protected OrderStatus(String name) {
		Validate.notNull(name, "OrderStatus name must not be null.");
		Validate.notEmpty(name, "OrderStatus name must not be empty.");
		this.name = name;
	}
	
	public String obtainName() {
		return this.name;
	}

	public static OrderStatus valueOf(String name){
		return new OrderStatus(name);
	}

	public OrderStatus changeStatus(String name) {
		Validate.notNull(name, "OrderStatus name must not be null.");
		Validate.notEmpty(name, "OrderStatus name must not be empty.");
		Validate.isTrue(!this.name.equals(name),"Can't change to the same status");
		return new OrderStatus(name);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    OrderStatus other = (OrderStatus) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean sameValueAs(OrderStatus other) {
		return other != null && new EqualsBuilder().append(this.name, other.name).isEquals();
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
