package udodog.goGetterServer.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.config.QuerydslConfig;
import udodog.goGetterServer.model.entity.QEvent;
import udodog.goGetterServer.model.entity.QUser;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {JpaAuditingConfig.class, QuerydslConfig.class}
))
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    void 회원_저장(){

        //given
        User user = User.builder()
                .email("hwoo00oo96@gmail.com")
                .password("1234")
                .name("변현우")
                .nickName("woo00oo")
                .phoneNumber("010-9245-7396")
                .grade(UserGrade.USER)
                .build();

        //when
        User saveUser = userRepository.save(user);

        //then
        assertThat(user).isEqualTo(saveUser);

    }

    @Test
    void 회원_조회(){
        //given
        User user = User.builder()
                .email("hwoo00oo96@gmail.com")
                .password("1234")
                .name("변현우")
                .nickName("woo00oo")
                .phoneNumber("010-9245-7396")
                .grade(UserGrade.USER)
                .build();

        User saveUser = userRepository.save(user);

        //when

        QUser qUser = QUser.user;

        QEvent event = QEvent.event;
        User findUser = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.email.eq("hwoo00oo96@gmail.com"))
                .fetchOne();

        //then
        Assertions.assertThat(findUser).isEqualTo(user);

    } // 회원_조회() 끝

    @Test
    void 전체회원_조회() {
        QUser qUser = QUser.user;

        List<User> findUserAll = jpaQueryFactory.selectFrom(qUser).fetch();

        Assertions.assertThat(findUserAll);
    } // 전체회원_조회() 끝

    @Test
    void Black회원_조회() {
        QUser qUser = QUser.user;

        List<User> findBKUserAll = jpaQueryFactory.selectFrom(qUser).where(qUser.grade.eq(UserGrade.BLACK)).fetch();

        Assertions.assertThat(findBKUserAll);
    } // Black회원_조회() 끝
} // Class 끝
