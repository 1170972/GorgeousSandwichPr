package pt.isep.arqsoft.GorgeousSandwich.domain.review;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;
import pt.isep.arqsoft.GorgeousSandwich.dto.review.GradeDTO;

public class Grade implements IValueObject<Grade>{
	
	private int value;
	
	public static int MIN_VALUE;
	
	public static int MAX_VALUE;
	
	protected Grade(int value) {
		Validate.notNull(value, "Grade value must not be null.");
		Validate.isTrue(value >= MIN_VALUE, "Grade value must be equal or superior than min value.");
		Validate.isTrue(value <= MAX_VALUE, "Grade value must be equal or inferior than max value.");
		this.value = value;
	}
	
	public int obtainValue() {
		return this.value;
	}
	
	public static Grade valueOf(int value){
		return new Grade(value);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Grade other = (Grade) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.value;
	}
		
	@Override
	public boolean sameValueAs(Grade other) {
		return other != null && new EqualsBuilder().append(this.value, other.value).isEquals();
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.value);
	}

	public static GradeDTO getMinMax(){
		return new GradeDTO(MIN_VALUE,MAX_VALUE);
	}

}
