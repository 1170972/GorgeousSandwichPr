package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;

@Repository
public interface ISandwichRepositoryJPA extends JpaRepository<SandwichPersistenceJPA, Long> {

    @Query(value = "SELECT c.* FROM SANDWICH c WHERE c.SANDWICH_ID IN?1",nativeQuery = true)
    List<SandwichPersistenceJPA> checkListOfSandwiches(List<Long> sandwichIds);
}
