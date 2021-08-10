package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class CouponUseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Coupon.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private String serialNumber;

    private LocalDate endDate;

    @Builder
    public CouponUseHistory(User user, Coupon coupon, String serialNumber, LocalDate endDate) {
        this.user = user;
        this.coupon = coupon;
        this.serialNumber = serialNumber;
        this.endDate = endDate;
    }
}
