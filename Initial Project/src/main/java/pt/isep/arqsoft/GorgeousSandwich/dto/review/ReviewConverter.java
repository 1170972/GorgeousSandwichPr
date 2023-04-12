package pt.isep.arqsoft.GorgeousSandwich.dto.review;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;

@Service
public class ReviewConverter {
	
    public ReviewDTO convertToDTO(Review review){
        if(review.obtainReviewId() == null){
            return new ReviewDTO(review.obtainDescription().obtainName(), review.obtainGrade().obtainValue(),
            		review.obtainSandwichId().obtainID(),review.obtainUserEmail().obtainName());
        }
        return new ReviewDTO(review.obtainReviewId().obtainID(), review.obtainDescription().obtainName(), review.obtainGrade().obtainValue(),
        		review.obtainSandwichId().obtainID(),review.obtainUserEmail().obtainName());
    }

    public Review convertFromDTO(ReviewDTO reviewDTO){
        return new Review(Description.valueOf(reviewDTO.description), Grade.valueOf(reviewDTO.grade), SandwichID.valueOf(reviewDTO.sandwichId), UserEmail.valueOf(reviewDTO.email));
    }

    public List<ReviewDTO> convertReviewListToDTO(List<Review> reviewList){
        List<ReviewDTO> reviewsDTO = new ArrayList<>();
        for (Review r : reviewList){
        	reviewsDTO.add(convertToDTO(r));
        }
        return reviewsDTO;
    }

}
