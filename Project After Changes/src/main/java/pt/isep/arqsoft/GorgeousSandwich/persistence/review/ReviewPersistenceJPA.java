package pt.isep.arqsoft.GorgeousSandwich.persistence.review;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;

import javax.persistence.*;

@Entity
@Table(name ="REVIEWS")
public class ReviewPersistenceJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @ManyToOne()
    @JoinColumn(name = "sandwich_id",nullable = false, foreignKey = @ForeignKey(name = "FK_sandwich_id_review"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SandwichPersistenceJPA sandwich;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name="grade",nullable = false)
    private int grade;

    @Column(name="email",nullable = false)
    private String email;

    public ReviewPersistenceJPA(SandwichPersistenceJPA sandwich, String description,int grade,String email) {
        this.sandwich = sandwich;
        this.description = description;
        this.grade = grade;
        this.email = email;
    }

    public ReviewPersistenceJPA(long reviewId, SandwichPersistenceJPA sandwich, String description,int grade,String email) {
        this.reviewId = reviewId;
        this.sandwich = sandwich;
        this.description = description;
        this.grade = grade;
        this.email = email;
    }

    public ReviewPersistenceJPA() {
    }

    public Long getReviewId() {
        return reviewId;
    }

    public SandwichPersistenceJPA getSandwich() {
        return sandwich;
    }

    public String getDescription() {
        return description;
    }

    public int getGrade() {
        return grade;
    }

    public String getEmail() {
        return email;
    }
}
