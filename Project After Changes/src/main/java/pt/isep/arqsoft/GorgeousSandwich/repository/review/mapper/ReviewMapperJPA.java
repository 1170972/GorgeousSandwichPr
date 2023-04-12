package pt.isep.arqsoft.GorgeousSandwich.repository.review.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.ReviewID;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;
import pt.isep.arqsoft.GorgeousSandwich.persistence.review.ReviewPersistenceJPA;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReviewMapperJPA {

    public static Review convertToDomain(ReviewPersistenceJPA persistence) {
        if(persistence.getReviewId() == null){
            return new Review(Description.valueOf(persistence.getDescription()), Grade.valueOf(persistence.getGrade()), SandwichID.valueOf(persistence.getSandwich().getSandwichId()), UserEmail.valueOf(persistence.getEmail()));
        }
        return new Review(Description.valueOf(persistence.getDescription()),Grade.valueOf(persistence.getGrade()), SandwichID.valueOf(persistence.getSandwich().getSandwichId()), ReviewID.valueOf(persistence.getReviewId()), UserEmail.valueOf(persistence.getEmail()));
    }

    public static ReviewPersistenceJPA convertToPersistence(Review domain) {
        SandwichPersistenceJPA sandwich = new SandwichPersistenceJPA(null,0,null,null,domain.obtainSandwichId().obtainID());
        if(domain.obtainReviewId() == null){
            return new ReviewPersistenceJPA(sandwich,domain.obtainDescription().obtainName(),domain.obtainGrade().obtainValue(),domain.obtainUserEmail().obtainName());
        }
        return new ReviewPersistenceJPA(domain.obtainReviewId().obtainID(),sandwich,domain.obtainDescription().obtainName(),domain.obtainGrade().obtainValue(),domain.obtainUserEmail().obtainName());
    }

    public static List<Review> convertListToDomain(List<ReviewPersistenceJPA> persistenceList) {
        List<Review> comments = new ArrayList<>();
        for (ReviewPersistenceJPA r:persistenceList) {
            comments.add(convertToDomain(r));
        }
        return comments;
    }

}
