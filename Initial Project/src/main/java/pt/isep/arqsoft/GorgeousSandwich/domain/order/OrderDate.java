package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import java.time.LocalDate;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class OrderDate implements IValueObject<OrderDate> {
	
	private LocalDate date;
	
	protected OrderDate(LocalDate date) {
		Validate.notNull(date, "OrderDate date must not be null.");
		this.date = date;
	}
	
	public LocalDate obtainDate() {
		return this.date;
	}

	public static OrderDate valueOf(LocalDate date){
		return new OrderDate(date);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    OrderDate other = (OrderDate) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.date.hashCode();
	}

	@Override
	public boolean sameValueAs(OrderDate other) {
		return other != null && new EqualsBuilder().append(this.date, other.date).isEquals();
	}
	
	@Override
	public String toString() {
		return this.date.toString();
	}

}
