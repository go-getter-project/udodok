package udodog.goGetterServer.repository.querydsl;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.config.TestConfig;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.CouponUseHistory;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.CouponRepository;
import udodog.goGetterServer.repository.CouponUseHistoryRepository;
import udodog.goGetterServer.repository.UserRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
@Slf4j
public class CouponQueryRepositoryTest {

    @Autowired
    private CouponQueryRepository couponQueryRepository;

    @Autowired
    private CouponUseHistoryRepository couponUseHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;


    @Test
    public void 쿠폰_중복다운_확인(){
        //given
        Coupon coupon = new Coupon("쿠폰",3000,30,1000);
        couponRepository.save(coupon);
        User user= new User();
        userRepository.save(user);
        CouponUseHistory couponUseHistory = new CouponUseHistory(user, coupon, "11111-2222222", LocalDate.now());
        CouponUseHistory savedCouponUseHistory = couponUseHistoryRepository.save(couponUseHistory);

        //when
        Boolean result = couponQueryRepository.overlapCoupon(savedCouponUseHistory.getUser().getId(), savedCouponUseHistory.getCoupon().getId());

        //then
        assertThat(result).isEqualTo(false);

    }




}