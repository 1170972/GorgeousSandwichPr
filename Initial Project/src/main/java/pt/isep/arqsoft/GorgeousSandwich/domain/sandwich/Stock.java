package pt.isep.arqsoft.GorgeousSandwich.domain.sandwich;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class Stock implements IValueObject<Stock> {
	
	private int units;
	
	protected Stock(int units) {
		Validate.notNull(units, "Stock units must not be null.");
		Validate.isTrue(units >= 0 , "Stock units must be 0 or more.");
		this.units = units;
	}
	
	public int obtainUnits() {
		return this.units;
	}

	public static Stock valueOf(int units){
		return new Stock(units);
	}
	
	public Stock addUnits(int units) {
		Validate.notNull(units, "Stock units must not be null.");
		Validate.isTrue(units > 0, "Stock units to add must be superior than 0.");
		return new Stock(this.units+units);
	}
	
	public Stock removeUnits(int units) {
		Validate.notNull(units, "Stock units must not be null.");
		Validate.isTrue(units > 0, "Stock units to remove must be superior than 0.");
		Validate.isTrue(this.units >= units,"Cant remove more units that those that exist");
		return new Stock(this.units - units);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Stock other = (Stock) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.units;
	}

	@Override
	public boolean sameValueAs(Stock other) {
		return other != null && new EqualsBuilder().append(this.units, other.units).isEquals();
	}
	
	@Override
	public String toString() {
		return Integer.toString(units);
	}
	
}
