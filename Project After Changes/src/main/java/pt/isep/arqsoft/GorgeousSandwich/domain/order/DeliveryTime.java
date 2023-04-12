package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import java.time.LocalTime;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;

public class DeliveryTime implements IValueObject<DeliveryTime> {
	
	private LocalTime startTime;
	private LocalTime endTime;

	private static List<DeliveryTimeDTO> possibleIntervals;
	
	protected DeliveryTime(LocalTime startTime, LocalTime endTime) {
		Validate.notNull(startTime, "DeliveryTime start time must not be null.");
		Validate.notNull(endTime, "DeliveryTime end time must not be null.");
		Validate.isTrue(endTime.isAfter(startTime), "End time must be after start time.");
		Validate.isTrue(possibleIntervals.contains(new DeliveryTimeDTO(startTime.toString(), endTime.toString())),"Invalid delivery time interval");
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public LocalTime obtainStartTime() {
		return this.startTime;
	}
	
	public LocalTime obtainEndTime() {
		return this.endTime;
	}

	public static List<DeliveryTimeDTO> obtainPossibleIntervals(){
		return possibleIntervals;
	}

	public static DeliveryTime valueOf(LocalTime startTime, LocalTime endTime){
		return new DeliveryTime(startTime, endTime);
	}

	public static DeliveryTime changeDeliveryTime(LocalTime start, LocalTime end){
		Validate.notNull(start, "DeliveryTime start time must not be null.");
		Validate.notNull(end, "DeliveryTime start time must not be null.");
		return new DeliveryTime(start,end);
	}

	public static void changePossibleIntervals(List<DeliveryTimeDTO> intervals){
		possibleIntervals = intervals;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    return sameValueAs((DeliveryTime) o);
	}
	
	@Override
	public int hashCode() {
		return this.startTime.hashCode() + this.endTime.hashCode();
	}

	@Override
	public boolean sameValueAs(DeliveryTime other) {
		return other != null && new EqualsBuilder().append(this.startTime, other.startTime).append(this.endTime, other.endTime).isEquals();
	}

}
