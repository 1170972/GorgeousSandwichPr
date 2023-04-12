package pt.isep.arqsoft.GorgeousSandwich.domain.comment;

import org.apache.commons.lang.Validate;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IEntity;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;

public class Comment implements IEntity<Comment> {
	
	private Description description;
	
	private SandwichID sandwichId;

	private UserEmail email;
	
	private CommentID commentId;
	
	public Comment(Description description, SandwichID sandwichId, UserEmail email, CommentID commentId) {
		Validate.noNullElements(new Object [] {description, sandwichId, email, commentId}, "Description, sandwich id and comment id must not be null.");
		this.description = description;
		this.sandwichId = sandwichId;
		this.email = email;
		this.commentId = commentId;
	}

	public Comment(Description description, SandwichID sandwichId, UserEmail email) {
		Validate.noNullElements(new Object [] {description, sandwichId,email}, "Description and sandwich id must not be null.");
		this.description = description;
		this.sandwichId = sandwichId;
		this.email = email;
	}
	
	public Description obtainDescription() {
		return this.description;
	}
	
	public CommentID obtainCommentId() {
		return this.commentId;
	}
	
	public SandwichID obtainSandwichId() {
		return this.sandwichId;
	}

	public UserEmail obtainEmail() { return email; }

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Comment other = (Comment) o;

	    return sameIdentityAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.commentId.hashCode();
	}

	@Override
	public boolean sameIdentityAs(Comment other) {
		return other != null && commentId.sameValueAs(other.commentId);
	}
	
	@Override
	public String toString() {
		return this.commentId.toString();
	}

}
