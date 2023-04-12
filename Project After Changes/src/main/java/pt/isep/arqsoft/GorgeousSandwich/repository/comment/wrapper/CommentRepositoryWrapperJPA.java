package pt.isep.arqsoft.GorgeousSandwich.repository.comment.wrapper;

import java.util.List;
import org.springframework.stereotype.Service;
import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.ICommentRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.mapper.CommentMapperJPA;

@Service("CommentRepositoryWrapperJPA")
public class CommentRepositoryWrapperJPA {

	private CommentMapperJPA mapper;
	private ICommentRepositoryJPA repository;

	public CommentRepositoryWrapperJPA(CommentMapperJPA mapper, ICommentRepositoryJPA repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	public Comment save(Comment model) {
		return this.mapper.convertToDomain(this.repository.save(mapper.convertToPersistence(model)));
	}

	public List<Comment> findBySandwichId(Long sandwichId) {
		return this.mapper.convertListToDomain(this.repository.findBySandwichId(sandwichId));
	}

	public List<Comment> findByEmail(String email) {
		return this.mapper.convertListToDomain(this.repository.findByEmail(email));
	}

}
