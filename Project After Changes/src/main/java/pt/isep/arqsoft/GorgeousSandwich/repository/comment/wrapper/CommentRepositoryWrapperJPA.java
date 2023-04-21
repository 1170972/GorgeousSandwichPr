package pt.isep.arqsoft.GorgeousSandwich.repository.comment.wrapper;

import java.util.List;
import org.springframework.stereotype.Service;
import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.ICommentRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.mapper.CommentMapperJPA;

@Service("CommentRepositoryWrapperJPA")
public class CommentRepositoryWrapperJPA {

	private ICommentRepositoryJPA repository;

	public CommentRepositoryWrapperJPA(ICommentRepositoryJPA repository) {
		this.repository = repository;
	}

	public Comment save(Comment model) {
		return CommentMapperJPA.convertToDomain(this.repository.save(CommentMapperJPA.convertToPersistence(model)));
	}

	public List<Comment> findBySandwichId(Long sandwichId) {
		return CommentMapperJPA.convertListToDomain(this.repository.findBySandwichId(sandwichId));
	}

	public List<Comment> findByEmail(String email) {
		return CommentMapperJPA.convertListToDomain(this.repository.findByEmail(email));
	}

}
