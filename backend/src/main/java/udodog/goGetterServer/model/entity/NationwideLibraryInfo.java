package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@NoArgsConstructor          // 기본 생성자
@Getter
public class NationwideLibraryInfo {
    @Id
    // 기본키 생성을 DB에 위임 시켜 준다. 즉, 자동 생성(시퀀스 넘버)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryInfoId;                 // 도서관 정보 식별번호

    private String libraryName;                 // 도서관 이름

    private String cityName;                    // 도시명

    private String sigunguName;                 // 시, 군, 구 이름

    private String libraryType;                 // 도서관 유형

    private String redDay;                      // 도서관 휴일

    private String weekdayStartTime;            // 평일 업무 시작 시간

    private String weekdayEndTime;              // 평일 업무 종료 시간

    private String saturdayStartTime;           // 토요일 업무 시작 시간

    private String saturdayEndTime;             // 토요일 업무 종료 시간

    private String publicHolidayStartTime;      // 공휴일 업무 시작 시간

    private String publicHolidayEndTime;        // 공휴일 업무 종료 시간

    private Long numberOfReadingSeats;          // 열람 좌석수

    private Long numberOfBooks;                 // 자료수(도서)

    private Long numberOfPublications;          // 자료수(연속간행물)

    private Long numberOfNotBooks;              // 자료수(비 도서)

    private Integer loanableCount;              // 대출 가능 도서수

    private Integer loanableDayTime;            // 대출 가능 일수

    private String roadAddress;                 // 소재지 도로명 주소

    private String operationInstitution;        // 운영기관

    private String phoneNumber;                 // 도서관 전화번호

    private String website;                     // 도서관 홈페이지 주소

    private String latitude;                    // 도서관 위치 위도값

    private String longtitude;                  // 도서관 위치 경도값

    private String dataInputDay;                // 정보 입력일

    @Builder
    public NationwideLibraryInfo(Long libraryInfoId, String libraryName, String cityName, String sigunguName, String libraryType, String redDay, String weekdayStartTime, String weekdayEndTime, String saturdayStartTime, String saturdayEndTime, String publicHolidayStartTime, String publicHolidayEndTime, Long numberOfReadingSeats, Long numberOfBooks, Long numberOfPublications, Long numberOfNotBooks, Integer loanableCount, Integer loanableDayTime, String roadAddress, String operationInstitution, String phoneNumber, String website, String latitude, String longtitude, String dataInputDay) {
        Assert.hasText(libraryName, "도서관 이름은 반드시 입력되어야 합니다.");
        Assert.hasText(sigunguName, "시, 군, 구 정보는 반드시 입력되어야 합니다.");
        Assert.hasText(roadAddress, "도로명 주소 정보는 반드시 입력되어야 합니다.");

        this.libraryInfoId = libraryInfoId;
        this.libraryName = libraryName;
        this.cityName = cityName;
        this.sigunguName = sigunguName;
        this.libraryType = libraryType;
        this.redDay = redDay;
        this.weekdayStartTime = weekdayStartTime;
        this.weekdayEndTime = weekdayEndTime;
        this.saturdayStartTime = saturdayStartTime;
        this.saturdayEndTime = saturdayEndTime;
        this.publicHolidayStartTime = publicHolidayStartTime;
        this.publicHolidayEndTime = publicHolidayEndTime;
        this.numberOfReadingSeats = numberOfReadingSeats;
        this.numberOfBooks = numberOfBooks;
        this.numberOfPublications = numberOfPublications;
        this.numberOfNotBooks = numberOfNotBooks;
        this.loanableCount = loanableCount;
        this.loanableDayTime = loanableDayTime;
        this.roadAddress = roadAddress;
        this.operationInstitution = operationInstitution;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.dataInputDay = dataInputDay;
    }  // 생성자 끝

} // Class End
