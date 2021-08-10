package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
