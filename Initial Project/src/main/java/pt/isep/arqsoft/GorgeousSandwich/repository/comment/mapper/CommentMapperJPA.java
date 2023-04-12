package pt.isep.arqsoft.GorgeousSandwich.repository.comment.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.domain.comment.CommentID;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;
import pt.isep.arqsoft.GorgeousSandwich.persistence.comment.CommentPersistenceJPA;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommentMapperJPA {

    public Comment convertToDomain(CommentPersistenceJPA persistence) {
        if(persistence.getCommentId() == null){
            return new Comment(Description.valueOf(persistence.getDescription()), SandwichID.valueOf(persistence.getSandwich().getSandwichId()), UserEmail.valueOf(persistence.getEmail()));
        }
        return new Comment(Description.valueOf(persistence.getDescription()), SandwichID.valueOf(persistence.getSandwich().getSandwichId()), UserEmail.valueOf(persistence.getEmail()), CommentID.valueOf(persistence.getCommentId()));
    }

    public CommentPersistenceJPA convertToPersistence(Comment domain) {
        SandwichPersistenceJPA sandwich = new SandwichPersistenceJPA(null,0,null,null,domain.obtainSandwichId().obtainID());
        if(domain.obtainCommentId() == null){
            return new CommentPersistenceJPA(sandwich,domain.obtainDescription().obtainName(),domain.obtainEmail().obtainName());
        }
        return new CommentPersistenceJPA(domain.obtainCommentId().obtainID(),sandwich,domain.obtainDescription().obtainName(),domain.obtainEmail().obtainName());
    }

    public List<Comment> convertListToDomain(List<CommentPersistenceJPA> persistenceList) {
        List<Comment> comments = new ArrayList<>();
        for (CommentPersistenceJPA c:persistenceList) {
            comments.add(convertToDomain(c));
        }
        return comments;
    }

    public List<CommentPersistenceJPA> convertListToPersistence(List<Comment> domainList) {
        List<CommentPersistenceJPA> comments = new ArrayList<>();
        for (Comment c:domainList) {
            comments.add(convertToPersistence(c));
        }
        return comments;
    }
}
