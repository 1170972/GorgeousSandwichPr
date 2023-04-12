package pt.isep.arqsoft.GorgeousSandwich.repository.comment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.isep.arqsoft.GorgeousSandwich.persistence.comment.CommentPersistenceJPA;

import java.util.List;

@Repository
public interface ICommentRepositoryJPA extends JpaRepository<CommentPersistenceJPA, Long> {
    @Query(value = "SELECT c.* FROM COMMENTS c WHERE c.SANDWICH_ID =?1",nativeQuery = true)
    List<CommentPersistenceJPA> findBySandwichId(long sandwichId);

    @Query(value = "SELECT c.* FROM COMMENTS c WHERE c.EMAIL =?1",nativeQuery = true)
    List<CommentPersistenceJPA> findByEmail(String email);
}
