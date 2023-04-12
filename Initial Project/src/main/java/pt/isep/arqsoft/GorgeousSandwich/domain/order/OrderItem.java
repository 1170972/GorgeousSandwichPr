package pt.isep.arqsoft.GorgeousSandwich.domain.order;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;

public class OrderItem implements IValueObject<OrderItem> {
	
	private SandwichID sandwichId;
	
	private Quantity quantity;
	
	protected OrderItem(SandwichID sandwichId, Quantity quantity) {
		Validate.noNullElements(new Object [] {sandwichId, quantity}, "The quantity and the sandwich id cannot be null.");
		this.sandwichId = sandwichId;
		this.quantity = quantity;
	}
	
	public SandwichID obtainSandwichId() {
		return this.sandwichId;
	}
	
	public Quantity obtainQuantity() {
		return this.quantity;
	}

	public static OrderItem valueOf(SandwichID sandwichId, Quantity quantity){
		return new OrderItem(sandwichId, quantity);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    OrderItem other = (OrderItem) o;

	    return sameValueAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.sandwichId.hashCode();
	}

	@Override
	public boolean sameValueAs(OrderItem other) {
		return other != null && new EqualsBuilder().append(this.sandwichId, other.sandwichId).append(this.quantity, other.quantity).isEquals();
	}

}
