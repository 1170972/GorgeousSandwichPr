package pt.isep.arqsoft.GorgeousSandwich.domain.sandwich;

import java.util.Hashtable;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.IValueObject;
import java.util.Map;

public enum Type implements IValueObject<Type> {
	SWEET("sweet"),
	SALTY("salty");

	private final String value;

	Type(String value) {
		this.value = value;
	}

	@Override
	public boolean sameValueAs(Type other) {
		return this.equals(other);
	}

	public static final Map<String, Type> nameIndex = new Hashtable<String, Type>();
	static{
		for(Type type : Type.values()){
			nameIndex.put(type.name(), type);
		}
	}

	public static Type lookupByName(String name){
		return nameIndex.get(name);
	}
}
