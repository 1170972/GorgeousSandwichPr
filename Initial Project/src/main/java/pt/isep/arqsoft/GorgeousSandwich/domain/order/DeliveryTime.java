package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;

public class DeliveryTime implements IValueObject<DeliveryTime> {
	
	private LocalTime startTime;
	
	private LocalTime endTime;

	public static LocalTime OpeningHours;
	public static LocalTime ClosingHours;
	public static int Interval;
	
	protected DeliveryTime(LocalTime startTime, LocalTime endTime) {
		Validate.notNull(startTime, "DeliveryTime start time must not be null.");
		Validate.notNull(endTime, "DeliveryTime end time must not be null.");
		Validate.isTrue(endTime.isAfter(startTime), "End time must be after start time.");
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public LocalTime obtainStartTime() {
		return this.startTime;
	}
	
	public LocalTime obtainEndTime() {
		return this.endTime;
	}

	public static DeliveryTime valueOf(LocalTime startTime, LocalTime endTime){
		return new DeliveryTime(startTime, endTime);
	}

	public DeliveryTime changeDeliveryTime(LocalTime start, LocalTime end){
		Validate.notNull(start, "DeliveryTime start time must not be null.");
		Validate.notNull(end, "DeliveryTime start time must not be null.");
		return new DeliveryTime(start,end);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    DeliveryTime other = (DeliveryTime) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.startTime.hashCode() + this.endTime.hashCode();
	}

	@Override
	public boolean sameValueAs(DeliveryTime other) {
		return other != null && new EqualsBuilder().append(this.startTime, other.startTime).append(this.endTime, other.endTime).isEquals();
	}

	public static List<DeliveryTimeDTO> calculateIntervals(){
		List<DeliveryTimeDTO> list = new ArrayList<>();
		LocalTime hours = OpeningHours;
		while(!hours.equals(ClosingHours)){
			LocalTime end = hours.plusMinutes(Interval);
			list.add(new DeliveryTimeDTO(hours.toString(),end.toString()));
			if(end.plusMinutes(Interval).isAfter(ClosingHours)){
				list.get(list.size()-1).endTime=ClosingHours.toString();
				break;
			}
			hours=end;
		}
		return list;
	}

}
