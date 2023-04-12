package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.wrapper;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Sandwich;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.ISandwichRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.mapper.SandwichMapperJPA;

@Service("SandwichRepositoryWrapperJPA")
public class SandwichRepositoryWrapperJPA {
	
	private SandwichMapperJPA mapper;
	
	private ISandwichRepositoryJPA repository;
	
	public SandwichRepositoryWrapperJPA(SandwichMapperJPA mapper, ISandwichRepositoryJPA repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	public Sandwich save(Sandwich model) {
		return this.mapper.convertToDomain(this.repository.save(this.mapper.convertToPersistence(model)));
	}

	public List<Sandwich> findAll() {
		return this.mapper.convertListToDomain(this.repository.findAll());
	}

	public boolean checkIfExists(Long Id) {
		return this.repository.findById(Id).isPresent();
	}

	public Sandwich getById(Long Id) {
		return this.mapper.convertToDomain(this.repository.findById(Id).get());
	}

	public Sandwich update(Sandwich model) {
		return this.mapper.convertToDomain(this.repository.save(this.mapper.convertToPersistence(model)));
	}

	public List<Sandwich> findByIds(Set<Long> sandwichIds) {
		return this.mapper.convertListToDomain(this.repository.findAllById(sandwichIds));
	}

}
