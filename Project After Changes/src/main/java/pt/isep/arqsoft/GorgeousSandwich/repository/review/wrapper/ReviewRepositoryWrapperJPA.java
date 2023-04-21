package pt.isep.arqsoft.GorgeousSandwich.repository.review.wrapper;

import java.util.List;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.IReviewRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.mapper.ReviewMapperJPA;

@Service("ReviewRepositoryWrapperJPA")
public class ReviewRepositoryWrapperJPA {
	
	private IReviewRepositoryJPA repository;
	
	public ReviewRepositoryWrapperJPA(IReviewRepositoryJPA repository) {
		this.repository = repository;
	}

	public Review save(Review model) {
		return ReviewMapperJPA.convertToDomain(this.repository.save(ReviewMapperJPA.convertToPersistence(model)));
	}

	public List<Review> findBySandwichId(Long sandwichId) {
		return ReviewMapperJPA.convertListToDomain(this.repository.findBySandwichId(sandwichId));
	}

	public List<Review> findByEmail(String email) {
		return ReviewMapperJPA.convertListToDomain(this.repository.findByEmail(email));
	}

}
