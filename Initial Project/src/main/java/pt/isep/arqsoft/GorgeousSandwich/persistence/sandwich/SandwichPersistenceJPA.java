package pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich;

import javax.persistence.*;

@Entity
@Table(name = "SANDWICHES")
public class SandwichPersistenceJPA {

    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "stock", nullable = false)
    private int stock;
    
    @Column(name = "designation", nullable = false, unique = true)
    private String designation;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sandwichId;

    public SandwichPersistenceJPA(String type, int stock, String designation, String description){
        this.type = type;
        this.stock = stock;
        this.designation = designation;
        this.description = description;
    }

    public SandwichPersistenceJPA(String type, int stock, String designation, String description, Long sandwichId) {
        this.type = type;
        this.stock = stock;
        this.designation = designation;
        this.description = description;
        this.sandwichId = sandwichId;
    }

    public SandwichPersistenceJPA(){

    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDescription() {
        return description;
    }

    public Long getSandwichId() {
        return sandwichId;
    }
}
