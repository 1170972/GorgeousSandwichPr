package pt.isep.arqsoft.GorgeousSandwich.repository.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.isep.arqsoft.GorgeousSandwich.persistence.review.ReviewPersistenceJPA;

@Repository
public interface IReviewRepositoryJPA extends JpaRepository<ReviewPersistenceJPA, Long> {
	
	@Query(value = "SELECT r.* FROM REVIEWS r WHERE r.SANDWICH_ID =?1", nativeQuery = true)
	List<ReviewPersistenceJPA> findBySandwichId(long sandwichId);

	@Query(value = "SELECT r.* FROM REVIEWS r WHERE r.EMAIL =?1", nativeQuery = true)
	List<ReviewPersistenceJPA> findByEmail(String email);

}
