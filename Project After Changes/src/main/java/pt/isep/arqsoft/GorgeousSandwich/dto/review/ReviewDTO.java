package pt.isep.arqsoft.GorgeousSandwich.dto.review;

public class ReviewDTO {

	public Long reviewId;
	
	public String description;
	
	public Integer grade;
	
	public Long sandwichId;

	public String email;
	
	public ReviewDTO() {

	}
	
	public ReviewDTO(Long reviewId, String description, Integer grade, Long sandwichId,String email) {
		this.reviewId = reviewId;
		this.description = description;
		this.grade = grade;
		this.sandwichId = sandwichId;
		this.email = email;
	}
	
	public ReviewDTO(String description, Integer grade, Long sandwichId, String email) {
		this.description = description;
		this.grade = grade;
		this.sandwichId = sandwichId;
		this.email = email;
	}
	
}
