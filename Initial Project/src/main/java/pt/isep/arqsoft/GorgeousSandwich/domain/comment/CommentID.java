package pt.isep.arqsoft.GorgeousSandwich.domain.comment;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class CommentID implements IValueObject<CommentID> {
	
	private long number;
	
	protected CommentID(long number) {
		Validate.notNull(number, "CommentID number must not be null.");
		this.number = number;
	}
	
	public long obtainID() {
		return this.number;
	}

	public static CommentID valueOf(long number){
		return new CommentID(number);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    CommentID other = (CommentID) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.number);
	}

	@Override
	public boolean sameValueAs(CommentID other) {
		return other != null && new EqualsBuilder().append(this.number, other.number).isEquals();
	}
	
	@Override
	public String toString() {
		return Long.toString(this.number);
	}

}
