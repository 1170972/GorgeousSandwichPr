package pt.isep.arqsoft.GorgeousSandwich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

@SpringBootApplication
public class GorgeousSandwichApplication {

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			 FileInputStream ip = new FileInputStream("./src/main/resources/config.properties");
			 properties.load(ip);
			 DeliveryTime.OpeningHours = LocalTime.parse(properties.getProperty("openingHours"));
			 DeliveryTime.ClosingHours = LocalTime.parse(properties.getProperty("closingHours"));
			 DeliveryTime.Interval = Integer.parseInt(properties.getProperty("interval"));
			Grade.MIN_VALUE = Integer.parseInt(properties.getProperty("grade.min"));
			Grade.MAX_VALUE = Integer.parseInt(properties.getProperty("grade.max"));
		}catch (IOException e){
			DeliveryTime.OpeningHours = LocalTime.parse("08:00");
			DeliveryTime.ClosingHours = LocalTime.parse("22:00");
			DeliveryTime.Interval = 20;
			Grade.MIN_VALUE = 1;
			Grade.MAX_VALUE = 5;
		}

		SpringApplication.run(GorgeousSandwichApplication.class, args);
	}

}
