package pt.isep.arqsoft.GorgeousSandwich.domain.shared;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

public class Description implements IValueObject<Description> {
	
	private String name;
	
	protected Description(String name) {
		Validate.notNull(name, "Description name must not be null.");
		Validate.notEmpty(name, "Description name must not be empty.");
		this.name = name;
	}
	
	public String obtainName() {
		return this.name;
	}

	public static Description valueOf(String name){
		return new Description(name);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Description other = (Description) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean sameValueAs(Description other) {
		return other != null && new EqualsBuilder().append(this.name, other.name).isEquals();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
