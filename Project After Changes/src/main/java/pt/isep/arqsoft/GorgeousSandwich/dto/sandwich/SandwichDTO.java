package pt.isep.arqsoft.GorgeousSandwich.dto.sandwich;

public class SandwichDTO {

    public Integer stock;
    
    public String type;
    
    public String designation;
    
    public String description;
    
    public Long sandwichId;

    public SandwichDTO(){

    }

    public SandwichDTO(Integer stock, String type, String designation, String description) {
        this.stock = stock;
        this.type = type;
        this.designation = designation;
        this.description = description;
    }

    public SandwichDTO(Long sandwichId, Integer stock, String type, String designation, String description) {
        this.sandwichId = sandwichId;
        this.stock = stock;
        this.type = type;
        this.designation = designation;
        this.description = description;
    }

}
