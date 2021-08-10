package udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.visualization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NationwidelibraryInfoVisualizationDto {

    private Long libraryInfoId;                 // 도서관 정보 식별번호

    private String libraryName;                 // 도서관 이름

    private String libraryType;                 // 도서관 유형

    private String redDay;                      // 도서관 휴일

    private String weekdayStartTime;            // 평일 업무 시작 시간

    private String weekdayEndTime;              // 평일 업무 종료 시간

    private String saturdayStartTime;           // 토요일 업무 시작 시간

    private String saturdayEndTime;             // 토요일 업무 종료 시간

    private String publicHolidayStartTime;      // 공휴일 업무 시작 시간

    private String publicHolidayEndTime;        // 공휴일 업무 종료 시간

    private String roadAddress;                 // 소재지 도로명 주소

    private String phoneNumber;                 // 도서관 전화번호

    private String website;                     // 도서관 홈페이지 주소

    private String latitude;                    // 도서관 위치 위도값

    private String longtitude;                  // 도서관 위치 경도값
} // Class 끝
