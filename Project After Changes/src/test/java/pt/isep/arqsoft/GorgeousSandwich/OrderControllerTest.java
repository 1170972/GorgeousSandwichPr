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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pt.isep.arqsoft.GorgeousSandwich.controller.OrderController;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.OrderDTO;
import pt.isep.arqsoft.GorgeousSandwich.dto.sandwich.SandwichDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @MockBean
    private OrderController orderController;

    private static SandwichDTO sandwich;
    private static SandwichDTO sandwich1;
    private static OrderDTO testOrder;
    private static OrderDTO testOrder1;
    private static DeliveryTimeDTO deliveryTime;

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

        deliveryTime = new DeliveryTimeDTO("13:40", "14:00");
        testOrder = new OrderDTO();
        testOrder.deliveryDate = "2023-03-30";
        testOrder.deliveryTime = deliveryTime;
        testOrder.email = "user1@test.com";

        testOrder1 = new OrderDTO();
        testOrder1.deliveryDate = "2023-03-30";
        testOrder1.deliveryTime = deliveryTime;
        testOrder1.email = "user2@test.com";
    }

    @Test
    public void createOrder() {
        when(orderController.createOrder(testOrder)).thenReturn(testOrder);
        assertEquals(testOrder, orderController.createOrder(testOrder));
    }

    @Test
    public void updateOrder() throws Exception {
        OrderDTO orderUpdated = new OrderDTO();
        orderUpdated.deliveryTime = deliveryTime;
        orderUpdated.deliveryDate = "2023-03-29";
        orderUpdated.email = "user1@test.com";
        ResponseEntity<OrderDTO> responseEntity = new ResponseEntity<>(orderUpdated, header, HttpStatus.OK);

        when(orderController.updateOrder(testOrder.orderId, orderUpdated)).thenReturn(responseEntity);
        assertEquals(orderUpdated, orderController.updateOrder(testOrder.orderId, orderUpdated).getBody());
    }

    @Test
    public void getOrdersByEmail() {
        when(orderController.getByEmail("user1@test.com")).thenReturn(Arrays.asList(testOrder));
        assertEquals(Arrays.asList(testOrder), orderController.getByEmail("user1@test.com"));
    }

    @Test
    public void getOrdersById() throws Exception {
        ResponseEntity<OrderDTO> responseEntity = new ResponseEntity<>(testOrder, header, HttpStatus.OK);

        when(orderController.getById(testOrder.orderId)).thenReturn(responseEntity);
        assertEquals(testOrder, orderController.getById(testOrder.orderId).getBody());
    }

    @Test
    public void getAllOrders() {
        when(orderController.listAll()).thenReturn(Arrays.asList(testOrder, testOrder1));
        assertEquals(Arrays.asList(testOrder, testOrder1), orderController.listAll());
    }
}
