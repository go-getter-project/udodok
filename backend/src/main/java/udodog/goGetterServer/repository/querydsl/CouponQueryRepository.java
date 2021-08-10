package udodog.goGetterServer.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static udodog.goGetterServer.model.entity.QCouponUseHistory.couponUseHistory;


@RequiredArgsConstructor
@Repository
public class CouponQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Boolean overlapCoupon(Long userId, Long couponId){

        Long couponUseHistoryId = queryFactory.select(couponUseHistory.id)
                .from(couponUseHistory)
                .where(couponUseHistory.user.id.eq(userId).and(couponUseHistory.coupon.id.eq(couponId)))
                .fetchOne();

        if(couponUseHistoryId == null){
            return true;
        }else{
            return false;
        }
    }


}
