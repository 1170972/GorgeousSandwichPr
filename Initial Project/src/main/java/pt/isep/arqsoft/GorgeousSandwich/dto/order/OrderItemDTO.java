package pt.isep.arqsoft.GorgeousSandwich.dto.order;

public class OrderItemDTO {

    public Long sandwichId;
    
    public Integer quantity;

    public OrderItemDTO(){

    }

    public OrderItemDTO(Long sandwichId, Integer quantity){
        this.sandwichId = sandwichId;
        this.quantity = quantity;

    }
}
