package pt.isep.arqsoft.GorgeousSandwich.controller;

import org.springframework.web.bind.annotation.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.dto.review.GradeDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.review.ReviewConverter;
import pt.isep.arqsoft.GorgeousSandwich.dto.review.ReviewDTO;

import java.util.List;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.wrapper.ReviewRepositoryWrapperJPA;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/gorgeous-sandwich")
public class ReviewController {
    private ReviewRepositoryWrapperJPA reviewRepository;

    public ReviewController(ReviewRepositoryWrapperJPA reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/reviews/sandwich/{id}")
    public List<ReviewDTO> listBySandwich(@PathVariable(value = "id") Long sandwichId){
        return ReviewConverter.convertReviewListToDTO(reviewRepository.findBySandwichId(sandwichId));
    }

    @GetMapping("/reviews/email/{id}")
    public List<ReviewDTO> listByEmail(@PathVariable(value = "id") String email){
        return ReviewConverter.convertReviewListToDTO(reviewRepository.findByEmail(email));
    }

    @GetMapping("reviews/gradeValues")
    public GradeDTO getGradeMinAndMax(){
        return Grade.getMinMax();
    }

    @PostMapping("/reviews")
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO){
        Review review = ReviewConverter.convertFromDTO(reviewDTO);
        if(review == null){
            throw new IllegalArgumentException("One or more of the input values are wrong");
        }
        return ReviewConverter.convertToDTO(reviewRepository.save(review));
    }
}
