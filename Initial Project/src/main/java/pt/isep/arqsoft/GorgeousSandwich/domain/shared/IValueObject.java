package pt.isep.arqsoft.GorgeousSandwich.domain.shared;

import java.io.Serializable;

public interface IValueObject<T> extends Serializable {
	
	boolean sameValueAs(T other);

}
