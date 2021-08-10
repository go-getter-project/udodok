package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.CouponUseHistory;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CouponUseHistoryRepositoryTest {

    @Autowired
    private CouponUseHistoryRepository couponUseHistoryRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 쿠폰사용이력_저장(){

        //given
        Coupon coupon = Coupon.builder()
                .name("문화상품권")
                .discount(3000)
                .quantity(30)
                .validDate(30)
                .build();

        User user = User.builder()
                .email("hwoo00oo96@gmail.com")
                .password("1234")
                .name("변현우")
                .nickName("woo00oo")
                .phoneNumber("010-9245-7396")
                .grade(UserGrade.USER)
                .build();


        User saveUser = userRepository.save(user);
        Coupon saveCoupon = couponRepository.save(coupon);

        CouponUseHistory couponUseHistory = CouponUseHistory.builder()
                .user(saveUser)
                .coupon(saveCoupon)
                .serialNumber("GHOGLGK30000")
                .endDate(LocalDate.of(2021,07,15))
                .build();

        //when
        CouponUseHistory saveCouponUseHistory = couponUseHistoryRepository.save(couponUseHistory);

        //then

        assertThat(couponUseHistory).isEqualTo(saveCouponUseHistory);
    }
}
