package pt.isep.arqsoft.GorgeousSandwich.repository.review.wrapper;

import java.util.List;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.IReviewRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.mapper.ReviewMapperJPA;

@Service("ReviewRepositoryWrapperJPA")
public class ReviewRepositoryWrapperJPA {
	
	private ReviewMapperJPA mapper;
	
	private IReviewRepositoryJPA repository;
	
	public ReviewRepositoryWrapperJPA(ReviewMapperJPA mapper, IReviewRepositoryJPA repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	public Review save(Review model) {
		return this.mapper.convertToDomain(this.repository.save(this.mapper.convertToPersistence(model)));
	}

	public List<Review> findBySandwichId(Long sandwichId) {
		return this.mapper.convertListToDomain(this.repository.findBySandwichId(sandwichId));
	}

	public List<Review> findByEmail(String email) {
		return this.mapper.convertListToDomain(this.repository.findByEmail(email));
	}

}
