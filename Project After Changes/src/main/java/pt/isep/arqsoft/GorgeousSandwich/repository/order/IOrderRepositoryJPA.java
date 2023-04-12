package pt.isep.arqsoft.GorgeousSandwich.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.isep.arqsoft.GorgeousSandwich.persistence.order.OrderPersistenceJPA;

import java.util.List;

@Repository
public interface IOrderRepositoryJPA extends JpaRepository<OrderPersistenceJPA, Long>{

    @Query(value = "SELECT o.* FROM ORDERS o WHERE o.EMAIL =?1", nativeQuery = true)
    List<OrderPersistenceJPA> findByEmail(String email);
}
