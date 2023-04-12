package pt.isep.arqsoft.GorgeousSandwich.domain.shared;

public interface IEntity<T> {
	
	boolean sameIdentityAs(T other);

}
