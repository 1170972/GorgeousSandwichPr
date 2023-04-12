package pt.isep.arqsoft.GorgeousSandwich;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Properties;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;

@SpringBootApplication
public class GorgeousSandwichApplication {

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			 FileInputStream ip = new FileInputStream("./src/main/resources/config.properties");
			 properties.load(ip);
			 DeliveryTime.changePossibleIntervals(calculateIntervals(LocalTime.parse(properties.getProperty("openingHours")),
																 LocalTime.parse(properties.getProperty("closingHours")),
																 Integer.parseInt(properties.getProperty("interval"))));
			Grade.changeMinMax(Integer.parseInt(properties.getProperty("grade.min")), Integer.parseInt(properties.getProperty("grade.max")));
		}catch (IOException e){
			DeliveryTime.changePossibleIntervals(calculateIntervals(LocalTime.parse("08:00"), LocalTime.parse("22:00"), 20));
			Grade.changeMinMax(1, 5);
		}

		SpringApplication.run(GorgeousSandwichApplication.class, args);
	}

	public static List<DeliveryTimeDTO> calculateIntervals(LocalTime openingHours, LocalTime closingHours, Integer interval){
		List<DeliveryTimeDTO> list = new ArrayList<>();
		LocalTime hours = openingHours;
		while(!hours.equals(closingHours)){
			LocalTime end = hours.plusMinutes(interval);
			list.add(new DeliveryTimeDTO(hours.toString(),end.toString()));
			if(end.plusMinutes(interval).isAfter(closingHours)){
				list.get(list.size()-1).endTime = closingHours.toString();
				break;
			}
			hours=end;
		}
		return list;
	}

}
