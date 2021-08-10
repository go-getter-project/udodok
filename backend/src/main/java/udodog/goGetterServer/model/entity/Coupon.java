package udodog.goGetterServer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer discount;

    private Integer validDate;

    private Integer quantity;

    @Builder
    public Coupon(String name, Integer discount, Integer validDate, Integer quantity) {
        this.name = name;
        this.discount = discount;
        this.validDate = validDate;
        this.quantity = quantity;
    }

    public void decreaseQuantity(){
        this.quantity = quantity - 1;
    }
}
