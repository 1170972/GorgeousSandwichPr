package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class Quantity implements IValueObject<Quantity> {
	
	private int units;
	
	protected Quantity(int units) {
		Validate.notNull(units, "Quantity units must not be null.");
		Validate.isTrue(units > 0 , "Quantity units must be superior than 0.");
		this.units=units;
	}
	
	public int obtainUnits() {
		return this.units;
	}

	public static Quantity valueOf(int units){
		return new Quantity(units);
	}

	public void changeQuantity(int units) {
		Validate.notNull(units, "Quantity units must not be null.");
		Validate.isTrue(units > 0, "Quantity units must be superior than 0.");
		Validate.isTrue(this.units != units,"Can't change to the same value");
		this.units = units;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Quantity other = (Quantity) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.units;
	}

	@Override
	public boolean sameValueAs(Quantity other) {
		return other != null && new EqualsBuilder().append(this.units, other.units).isEquals();
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.units);
	}

}
