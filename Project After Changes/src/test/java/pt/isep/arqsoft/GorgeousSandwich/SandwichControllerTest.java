package pt.isep.arqsoft.GorgeousSandwich;

import java.time.LocalTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import pt.isep.arqsoft.GorgeousSandwich.controller.SandwichController;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.dto.sandwich.SandwichDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SandwichControllerTest {

    @MockBean
    private SandwichController sandwichController;

    private static SandwichDTO sandwich;
    private static SandwichDTO testSandwich1;
    private static SandwichDTO testSandwich2;
    private static List<SandwichDTO> allsandwich;

    private static HttpHeaders header = new HttpHeaders();

    @BeforeClass
    public static void setup(){
        DeliveryTime.changePossibleIntervals(GorgeousSandwichApplication.calculateIntervals(LocalTime.parse("08:00"), LocalTime.parse("22:00"), 20));
        Grade.changeMinMax(1, 5);
        header.setContentType(MediaType.APPLICATION_JSON);

        sandwich = new SandwichDTO();
        sandwich.stock = 1;
        sandwich.type = "salty";
        sandwich.designation = "Test Sandwich 1";
        sandwich.description = "Description of Sandwich 1";

        testSandwich1 = new SandwichDTO();
        testSandwich1.stock = 2;
        testSandwich1.type = "sweet";
        testSandwich1.designation = "Test Sandwich 2";
        testSandwich1.description = "Description of Sandwich 2";

        testSandwich2 = new SandwichDTO();
        testSandwich2.stock = 3;
        testSandwich2.type = "sweet";
        testSandwich2.designation = "Test Sandwich 3";
        testSandwich2.description = "Description of Sandwich 3";

        allsandwich = new ArrayList<>();
        allsandwich.add(sandwich);
        allsandwich.add(testSandwich1);
        allsandwich.add(testSandwich2);
    }

    @Test
    public void addSandwich() {
        when(sandwichController.createSandwich(sandwich)).thenReturn(sandwich);
        assertEquals(sandwich, sandwichController.createSandwich(sandwich));
    }

    @Test
    public void changeStockSandwich() throws Exception {
        SandwichDTO sandwichUpdated = sandwich;
        sandwichUpdated.stock = 2;
        ResponseEntity<SandwichDTO> responseEntity = new ResponseEntity<>(sandwichUpdated, header, HttpStatus.OK);

        when(sandwichController.addUnitsSandwich(sandwich.sandwichId, sandwichUpdated)).thenReturn(responseEntity);
        assertEquals(sandwichUpdated, sandwichController.addUnitsSandwich(sandwich.sandwichId, sandwichUpdated).getBody());
    }

    @Test
    public void getAllSandwiches() {
        when(sandwichController.listAll()).thenReturn(allsandwich);
        assertEquals(allsandwich, sandwichController.listAll());
    }

    @Test
    public void getSandwichById() throws Exception{
        ResponseEntity<SandwichDTO> responseEntity = new ResponseEntity<>(sandwich, header, HttpStatus.OK);

        when(sandwichController.getById(sandwich.sandwichId)).thenReturn(responseEntity);
        assertEquals(sandwich, sandwichController.getById(sandwich.sandwichId).getBody());
    }
}
