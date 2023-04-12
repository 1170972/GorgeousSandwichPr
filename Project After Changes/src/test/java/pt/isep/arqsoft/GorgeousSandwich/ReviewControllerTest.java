package pt.isep.arqsoft.GorgeousSandwich;

import java.time.LocalTime;
import java.util.Arrays;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import pt.isep.arqsoft.GorgeousSandwich.controller.ReviewController;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.dto.review.GradeDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.review.ReviewDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.sandwich.SandwichDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @MockBean
    private ReviewController reviewController;

    private static SandwichDTO sandwich;
    private static SandwichDTO sandwich1;
    private static ReviewDTO review;
    private static ReviewDTO review1;
    private static ReviewDTO review2;
    private static GradeDTO grade;

    private static HttpHeaders header = new HttpHeaders();

    @BeforeClass
    public static void setup(){
        grade = new GradeDTO(1, 5);

        DeliveryTime.changePossibleIntervals(GorgeousSandwichApplication.calculateIntervals(LocalTime.parse("08:00"), LocalTime.parse("22:00"), 20));
        Grade.changeMinMax(grade.min, grade.max);
        header.setContentType(MediaType.APPLICATION_JSON);

        sandwich = new SandwichDTO();
        sandwich.stock = 1;
        sandwich.type = "salty";
        sandwich.designation = "Sandwich 1";
        sandwich.description = "Description of Sandwich 1";

        sandwich1 = new SandwichDTO();
        sandwich1.stock = 1;
        sandwich1.type = "sweet";
        sandwich1.designation = "Sandwich 2";
        sandwich1.description = "Description of Sandwich 2";

        review = new ReviewDTO();
        review.sandwichId = sandwich.sandwichId;
        review.description = "Review On Sandwich";
        review.email = "user1@test.com";
        review.grade = 2;

        review1 = new ReviewDTO();
        review1.sandwichId = sandwich1.sandwichId;
        review1.description = "Review On Sandwich 1";
        review1.email = "user1@test.com";
        review.grade = 3;

        review2 = new ReviewDTO();
        review2.sandwichId = sandwich1.sandwichId;
        review2.description = "Review On Sandwich 1";
        review2.email = "user2@test.com";
        review.grade = 3;
    }

    @Test
    public void createReview() {
        when(reviewController.createReview(review)).thenReturn(review);
        assertEquals(review, reviewController.createReview(review));
    }

    @Test
    public void getReviewsBySandwich() {
        when(reviewController.listBySandwich(sandwich1.sandwichId)).thenReturn(Arrays.asList(review1, review2));
        assertEquals(Arrays.asList(review1, review2), reviewController.listBySandwich(sandwich1.sandwichId));
    }

    @Test
    public void getReviewsByEmail() {
        when(reviewController.listByEmail("user1@test.com")).thenReturn(Arrays.asList(review, review1));
        assertEquals(Arrays.asList(review, review1), reviewController.listByEmail("user1@test.com"));
    }

    @Test
    public void getGradeMinAndMax() {
        when(reviewController.getGradeMinAndMax()).thenReturn(grade);
        assertEquals(grade, reviewController.getGradeMinAndMax());
    }

}
