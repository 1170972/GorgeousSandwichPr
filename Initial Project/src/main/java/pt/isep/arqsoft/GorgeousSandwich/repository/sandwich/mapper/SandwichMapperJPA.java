package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SandwichMapperJPA {

	public Sandwich convertToDomain(SandwichPersistenceJPA persistence) {
		if(persistence.getSandwichId() == null){
			return new Sandwich(Type.valueOf(persistence.getType()), Stock.valueOf(persistence.getStock()), Designation.valueOf(persistence.getDesignation()), Description.valueOf(persistence.getDescription()));
		}
		return new Sandwich(Type.valueOf(persistence.getType()), Stock.valueOf(persistence.getStock()), Designation.valueOf(persistence.getDesignation()), Description.valueOf(persistence.getDescription()), SandwichID.valueOf(persistence.getSandwichId()));
	}

	public SandwichPersistenceJPA convertToPersistence(Sandwich domain) {
		if(domain.obtainSandwichID() == null){
			return new SandwichPersistenceJPA(domain.obtainType().name(), domain.obtainStock().obtainUnits(), domain.obtainDesignation().obtainName(), domain.obtainDescription().obtainName());
		}
		return new SandwichPersistenceJPA(domain.obtainType().name(), domain.obtainStock().obtainUnits(), domain.obtainDesignation().obtainName(), domain.obtainDescription().obtainName(), domain.obtainSandwichID().obtainID());
	}

	public List<Sandwich> convertListToDomain(List<SandwichPersistenceJPA> persistenceList) {
		List<Sandwich> sandwiches = new ArrayList<>();
		for (SandwichPersistenceJPA s : persistenceList) {
			sandwiches.add(convertToDomain(s));
		}
		return sandwiches;
	}

	public List<SandwichPersistenceJPA> convertListToPersistence(List<Sandwich> domainList) {
		List<SandwichPersistenceJPA> sandwiches = new ArrayList<>();
		for (Sandwich s : domainList) {
			sandwiches.add(convertToPersistence(s));
		}
		return sandwiches;
	}

}
