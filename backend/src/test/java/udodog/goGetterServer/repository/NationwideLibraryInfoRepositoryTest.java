package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.NationwideLibraryInfo;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NationwideLibraryInfoRepositoryTest {

    @Autowired
    private NationwideLibraryInfoRepository nationwideLibraryInfoRepository;

    @Test
    void 도서관_위치_저장() {

          // given → 테스트를 준비하는 과정 (~가 주어지고)
        NationwideLibraryInfo nationLibraryInfo = NationwideLibraryInfo.builder()
                .libraryName("우도독")
                .cityName("경기도")
                .sigunguName("성남시 분당구")
                .libraryType("공공도서관")
                .redDay("매주 주일")
                .weekdayStartTime("9:00")
                .weekdayEndTime("22:00")
                .saturdayStartTime("9:00")
                .saturdayEndTime("19:00")
                .publicHolidayStartTime("9:00")
                .publicHolidayEndTime("22:00")
                .numberOfReadingSeats(3344L)
                .numberOfBooks(394855L)
                .numberOfPublications(38473L)
                .numberOfNotBooks(38473L)
                .loanableCount(39)
                .loanableDayTime(14)
                .roadAddress("경기도 성남시 분당구 우도독 59, 주니하랑(주)")
                .operationInstitution("Go-Getter")
                .phoneNumber("031-383-4984")
                .website("http://www.udodok.com")
                .latitude("73.4948488")
                .longtitude("138.38388")
                .dataInputDay("2021-07-22")
                .build();

          // when → 실제로 테스트를 실행하는 과정 (~을 했을 때)
        NationwideLibraryInfo saveLibraryInformation = nationwideLibraryInfoRepository.save(nationLibraryInfo);

          // then → 테스트를 검증하는 과정 (~한 값이 나와야 함.)
        assertThat(nationLibraryInfo).isEqualTo(saveLibraryInformation);

    } // Method End

} // Class 끝