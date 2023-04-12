package pt.isep.arqsoft.GorgeousSandwich.dto.comment;

public class CommentDTO {
	
	public Long commentId;
	
	public String description;
	
	public Long sandwichId;

	public String email;
	
	public CommentDTO() {

	}
	
	public CommentDTO(Long commentId, String description, Long sandwichId, String email) {
		this.commentId = commentId;
		this.description = description;
		this.sandwichId = sandwichId;
		this.email = email;
	}
	
	public CommentDTO(String description, Long sandwichId, String email) {
		this.description = description;
		this.sandwichId = sandwichId;
		this.email = email;
	}
	
	

}
