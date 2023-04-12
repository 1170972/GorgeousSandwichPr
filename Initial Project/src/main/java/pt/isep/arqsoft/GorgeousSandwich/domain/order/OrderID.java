package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class OrderID implements IValueObject<OrderID> {
	
	private long number;
	
	protected OrderID(long number) {
		Validate.notNull(number, "OrderID number must not be null.");
		this.number = number;
	}
	
	public long obtainID() {
		return this.number;
	}

	public static OrderID valueOf(long number){
		return new OrderID(number);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    OrderID other = (OrderID) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.number);
	}

	@Override
	public boolean sameValueAs(OrderID other) {
		return other != null && new EqualsBuilder().append(this.number, other.number).isEquals();
	}
	
	@Override
	public String toString() {
		return Long.toString(this.number);
	}

}
