package udodog.goGetterServer.model.dto.request.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.Coupon;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponCreateRequestDto {

    @NotEmpty
    private String name;

    @NotNull
    private Integer discount;

    @NotNull
    private Integer validDate;

    @NotNull
    private Integer quantity;

    @Builder
    public Coupon toEntity(){

        return Coupon.builder()
                .name(name)
                .discount(discount)
                .quantity(quantity)
                .validDate(validDate)
                .build();

    }

}
