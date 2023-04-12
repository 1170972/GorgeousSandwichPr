package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;

@Repository
public interface ISandwichRepositoryJPA extends JpaRepository<SandwichPersistenceJPA, Long> {

}
