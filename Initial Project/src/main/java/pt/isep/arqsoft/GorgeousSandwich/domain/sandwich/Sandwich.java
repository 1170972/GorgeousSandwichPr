package pt.isep.arqsoft.GorgeousSandwich.domain.sandwich;

import org.apache.commons.lang.Validate;

import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IEntity;

public class Sandwich implements IEntity<Sandwich> {
	
	private Type type;
	
	private Stock stock;
	
	private Designation designation;
	
	private Description description;
	
	private SandwichID sandwichId;
	
	public Sandwich(Type type, Stock stock, Designation designation, Description description) {
		Validate.noNullElements(new Object[] {type, stock, designation, description}, "The type, stock, designation and description cannot be null.");
		this.type = type;
		this.stock = stock;
		this.designation = designation;
		this.description = description;
	}

	public Sandwich(Type type, Stock stock, Designation designation, Description description, SandwichID sandwichId) {
		Validate.noNullElements(new Object[] {type, stock, designation, description,sandwichId}, "The type, stock, designation, description and sandwichId cannot be null.");
		this.type = type;
		this.stock = stock;
		this.designation = designation;
		this.description = description;
		this.sandwichId = sandwichId;
	}

	public Type obtainType() {
		return this.type;
	}
	
	public Stock obtainStock() {
		return this.stock;
	}
	
	public Designation obtainDesignation() {
		return this.designation;
	}
	
	public Description obtainDescription() {
		return this.description;
	}
	
	public SandwichID obtainSandwichID() {
		return this.sandwichId;
	}

	public void changeStock(int value){
		if(this.stock.obtainUnits() < value){
			this.stock = stock.addUnits(value-stock.obtainUnits());
		}
		if(this.stock.obtainUnits() > value){
			this.stock = stock.removeUnits(stock.obtainUnits()-value);
		}
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Sandwich other = (Sandwich) o;

	    return sameIdentityAs(other);
	}
	
	@Override
	public int hashCode() {
		return this.sandwichId.hashCode();
	}

	@Override
	public boolean sameIdentityAs(Sandwich other) {
		return other != null && sandwichId.sameValueAs(other.sandwichId);
	}
	
	@Override
	public String toString() {
		return this.sandwichId.toString();
	}

}
