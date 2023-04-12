package pt.isep.arqsoft.GorgeousSandwich.domain.sandwich;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class SandwichID implements IValueObject<SandwichID> {
	
	private long number;
	
	protected SandwichID(long number) {
		Validate.notNull(number, "SandwichID number must not be null.");
		this.number = number;
	}
	
	public long obtainID() {
		return this.number;
	}

	public static SandwichID valueOf(long number){
		return new SandwichID(number);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    SandwichID other = (SandwichID) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.number);
	}

	@Override
	public boolean sameValueAs(SandwichID other) {
		return other != null && new EqualsBuilder().append(this.number, other.number).isEquals();
	}
	
	@Override
	public String toString() {
		return Long.toString(this.number);
	}

}
