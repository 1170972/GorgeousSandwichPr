package pt.isep.arqsoft.GorgeousSandwich.dto.order;

public class OrderItemDTO implements Comparable<OrderItemDTO>{

    public Long sandwichId;
    
    public Integer quantity;

    public OrderItemDTO(Long sandwichId, Integer quantity){
        this.sandwichId = sandwichId;
        this.quantity = quantity;

    }

    @Override
    public int compareTo(OrderItemDTO o) {
        if (sandwichId > o.sandwichId) {
            return 1;
        }
        else if (sandwichId < o.sandwichId) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
