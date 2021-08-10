package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.CouponUseHistory;

public interface CouponUseHistoryRepository extends JpaRepository<CouponUseHistory, Long> {
}
