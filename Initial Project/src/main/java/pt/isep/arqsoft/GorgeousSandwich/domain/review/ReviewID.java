package pt.isep.arqsoft.GorgeousSandwich.domain.review;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class ReviewID implements IValueObject<ReviewID> {

	private long number;
	
	protected ReviewID(long number) {
		Validate.notNull(number, "ReviewID number must not be null.");
		this.number = number;
	}
	
	public long obtainID() {
		return this.number;
	}

	public static ReviewID valueOf(long number){
		return new ReviewID(number);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    ReviewID other = (ReviewID) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.number);
	}

	@Override
	public boolean sameValueAs(ReviewID other) {
		return other != null && new EqualsBuilder().append(this.number, other.number).isEquals();
	}
	
	@Override
	public String toString() {
		return Long.toString(this.number);
	}
	
}
