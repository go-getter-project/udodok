package udodog.goGetterServer.model.dto.request.nationwidelibraryinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.NationwideLibraryInfo;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NationwideLibraryInfoInsertReqeustDto {

    @NotEmpty private String libraryName;                 // 도서관 이름

    @NotEmpty private String cityName;                    // 도시명

    @NotEmpty private String sigunguName;                 // 시, 군, 구 이름

    @NotEmpty private String libraryType;                 // 도서관 유형

    @NotEmpty private String redDay;                      // 도서관 휴일

    @NotEmpty private String weekdayStartTime;            // 평일 업무 시작 시간

    @NotEmpty private String weekdayEndTime;              // 평일 업무 종료 시간

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

    @NotEmpty private String roadAddress;                 // 소재지 도로명 주소

    private String operationInstitution;                    // 운영기관

    @NotEmpty private String phoneNumber;                 // 도서관 전화번호

    private String website;                                 // 도서관 홈페이지 주소

    @NotEmpty private String latitude;                    // 도서관 위치 위도값

    @NotEmpty private String longtitude;                  // 도서관 위치 경도값

    @NotEmpty private String dataInputDay;                // 정보 입력일

    @Builder
    public NationwideLibraryInfo toEntity (NationwideLibraryInfoInsertReqeustDto infoInsertReqeustDto) {
        return NationwideLibraryInfo.builder()
                .libraryName(infoInsertReqeustDto.libraryName)
                .cityName(infoInsertReqeustDto.cityName)
                .sigunguName(infoInsertReqeustDto.sigunguName)
                .libraryType(infoInsertReqeustDto.libraryType)
                .redDay(infoInsertReqeustDto.redDay)
                .weekdayStartTime(infoInsertReqeustDto.weekdayStartTime)
                .weekdayEndTime(infoInsertReqeustDto.weekdayEndTime)
                .numberOfReadingSeats(infoInsertReqeustDto.numberOfReadingSeats)
                .numberOfBooks(infoInsertReqeustDto.numberOfBooks)
                .numberOfPublications(infoInsertReqeustDto.numberOfPublications)
                .numberOfNotBooks(infoInsertReqeustDto.numberOfNotBooks)
                .loanableCount(infoInsertReqeustDto.loanableCount)
                .loanableDayTime(infoInsertReqeustDto.loanableDayTime)
                .roadAddress(infoInsertReqeustDto.roadAddress)
                .phoneNumber(infoInsertReqeustDto.phoneNumber)
                .latitude(infoInsertReqeustDto.latitude)
                .longtitude(infoInsertReqeustDto.longtitude)
                .dataInputDay(infoInsertReqeustDto.dataInputDay)
                .build();
    } //toEntity() 끝

}// Class 끝
