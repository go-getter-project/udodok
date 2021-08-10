package udodog.goGetterServer.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookReportTagRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookReportRepository bookReportRepository;

    @Autowired
    private BookReportTagRepository bookReportTagRepository;

    @Test
    void 테그_저장(){

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
        BookReport bookReport = BookReport.builder()
                .user(saveUser)
                .title("공부 1일차")
                .bookName("누가 내 머리에 똥 샀어!")
                .content("독서 기록입니다")
                .build();

        BookReport saveBookReport = bookReportRepository.save(bookReport);

        BookReportTag bookReportTag = BookReportTag.builder()
                .bookReport(saveBookReport)
                .tagName("스프링")
                .build();

        //when
        BookReportTag saveBookReportTag = bookReportTagRepository.save(bookReportTag);

        //then
        Assertions.assertThat(bookReportTag).isEqualTo(saveBookReportTag);

    }
}
