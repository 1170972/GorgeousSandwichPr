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
import pt.isep.arqsoft.GorgeousSandwich.controller.CommentController;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.dto.comment.CommentDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.sandwich.SandwichDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @MockBean
    private CommentController commentController;

    private static SandwichDTO sandwich;
    private static SandwichDTO sandwich1;
    private static CommentDTO comment;
    private static CommentDTO comment1;
    private static CommentDTO comment2;

    private static HttpHeaders header = new HttpHeaders();

    @BeforeClass
    public static void setup(){
        DeliveryTime.changePossibleIntervals(GorgeousSandwichApplication.calculateIntervals(LocalTime.parse("08:00"), LocalTime.parse("22:00"), 20));
        Grade.changeMinMax(1, 5);
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

        comment = new CommentDTO();
        comment.sandwichId = sandwich.sandwichId;
        comment.description = "Comment On Sandwich";
        comment.email = "user1@test.com";

        comment1 = new CommentDTO();
        comment1.sandwichId = sandwich1.sandwichId;
        comment1.description = "Comment On Sandwich 1";
        comment1.email = "user1@test.com";

        comment2 = new CommentDTO();
        comment2.sandwichId = sandwich1.sandwichId;
        comment2.description = "Comment On Sandwich 1";
        comment2.email = "user2@test.com";
    }

    @Test
    public void createComment() {
        when(commentController.createComment(comment)).thenReturn(comment);
        assertEquals(comment, commentController.createComment(comment));
    }

    @Test
    public void getCommentsBySandwich() {
        when(commentController.listBySandwich(sandwich1.sandwichId)).thenReturn(Arrays.asList(comment1, comment2));
        assertEquals(Arrays.asList(comment1, comment2), commentController.listBySandwich(sandwich1.sandwichId));
    }

    @Test
    public void getCommentsByEmail() {
        when(commentController.listByEmail("user1@test.com")).thenReturn(Arrays.asList(comment, comment1));
        assertEquals(Arrays.asList(comment, comment1), commentController.listByEmail("user1@test.com"));
    }
}
