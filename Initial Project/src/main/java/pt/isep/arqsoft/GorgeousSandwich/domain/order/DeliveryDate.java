package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import java.time.LocalDate;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class DeliveryDate implements IValueObject<DeliveryDate> {
	
	private LocalDate date;
	
	protected DeliveryDate(LocalDate date) {
		Validate.notNull(date, "DeliveryDate date must not be null.");
		this.date = date;
	}
	
	public LocalDate obtainDate() {
		return this.date;
	}

	public static DeliveryDate valueOf(LocalDate date){
		return new DeliveryDate(date);
	}

	public DeliveryDate changeDate(LocalDate date) {
		Validate.notNull(date, "DeliveryDate date must not be null.");
		Validate.isTrue(this.date != date,"Delivery date cannot be the same");
		return new DeliveryDate(date);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    DeliveryDate other = (DeliveryDate) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.date.hashCode();
	}

	@Override
	public boolean sameValueAs(DeliveryDate other) {
		return other != null && new EqualsBuilder().append(this.date, other.date).isEquals();
	}
	
	@Override
	public String toString() {
		return this.date.toString();
	}

}
