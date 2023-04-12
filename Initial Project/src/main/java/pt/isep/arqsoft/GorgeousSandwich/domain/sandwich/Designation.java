package pt.isep.arqsoft.GorgeousSandwich.domain.sandwich;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class Designation implements IValueObject<Designation> {
	
	private String name;
	
	protected Designation(String name) {
		Validate.notNull(name, "Designation name must not be null.");
		Validate.notEmpty(name, "Designation name must not be empty.");
		this.name = name;
	}
	
	public String obtainName() {
		return this.name;
	}

	public static Designation valueOf(String name){
		return new Designation(name);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Designation other = (Designation) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean sameValueAs(Designation other) {
		return other != null && new EqualsBuilder().append(this.name, other.name).isEquals();
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
