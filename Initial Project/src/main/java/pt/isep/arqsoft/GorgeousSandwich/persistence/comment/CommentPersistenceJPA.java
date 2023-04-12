package pt.isep.arqsoft.GorgeousSandwich.persistence.comment;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;

import javax.persistence.*;

@Entity
@Table(name ="COMMENTS")
public class CommentPersistenceJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @ManyToOne()
    @JoinColumn(name = "sandwich_id",nullable = false, foreignKey = @ForeignKey(name = "FK_sandwich_id_comment"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SandwichPersistenceJPA sandwich;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "email",nullable = false)
    private String email;

    public CommentPersistenceJPA(SandwichPersistenceJPA sandwich, String description,String email) {
        this.sandwich = sandwich;
        this.description = description;
        this.email = email;
    }

    public CommentPersistenceJPA(long commentId, SandwichPersistenceJPA sandwich, String description, String email) {
        this.commentId = commentId;
        this.sandwich = sandwich;
        this.description = description;
        this.email = email;
    }

    public CommentPersistenceJPA() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public SandwichPersistenceJPA getSandwich() {
        return sandwich;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() { return email; }
}
