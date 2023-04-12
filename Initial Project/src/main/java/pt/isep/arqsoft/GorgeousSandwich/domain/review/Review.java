package pt.isep.arqsoft.GorgeousSandwich.domain.review;

import org.apache.commons.lang.Validate;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IEntity;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;

public class Review implements IEntity<Review> {

	private Description description;
	
	private Grade grade;
	
	private SandwichID sandwichId;

	private UserEmail email;
	
	private ReviewID reviewId;
	
	public Review(Description description, Grade grade, SandwichID sandwichId, ReviewID reviewId,UserEmail email) {
		Validate.noNullElements(new Object [] {description, grade, sandwichId, reviewId,email}, "Description, grade, sandwich id and review id must not be null.");
		this.description = description;
		this.grade = grade;
		this.sandwichId = sandwichId;
		this.reviewId = reviewId;
		this.email = email;
	}

	
	public Review(Description description, Grade grade, SandwichID sandwichId,UserEmail email) {
		Validate.noNullElements(new Object [] {description, grade, sandwichId,email}, "Description, grade and sandwich id must not be null.");
		this.description = description;
		this.grade = grade;
		this.sandwichId = sandwichId;
		this.email = email;
	}
	
	public Description obtainDescription() {
		return this.description;
	}
	
	public Grade obtainGrade() {
		return this.grade;
	}
	
	public ReviewID obtainReviewId() {
		return this.reviewId;
	}
	
	public SandwichID obtainSandwichId() {
		return this.sandwichId;
	}

	public UserEmail obtainUserEmail(){return this.email;}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Review other = (Review) o;

	    return sameIdentityAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.reviewId.hashCode();
	}

	@Override
	public boolean sameIdentityAs(Review other) {
		return other != null && reviewId.sameValueAs(other.reviewId);
	}
	
	@Override
	public String toString() {
		return this.reviewId.toString();
	}
	
	
}
