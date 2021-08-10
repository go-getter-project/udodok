package udodog.goGetterServer.model.dto.response.bookreporttag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookReportTagResponseDto {

    private Long bookReportTagId;           // Tag 번호
    private Long bookReportId;              // 독서 기록 글 번호
    private String tagName;                 // Tag 내용

    public BookReportTagResponseDto ( BookReportTagResponseDto bookReportTagResponseDto ) {

        this.bookReportTagId = bookReportTagResponseDto.bookReportTagId;
        this.bookReportId = bookReportTagResponseDto.bookReportId;
        this.tagName = bookReportTagResponseDto.tagName;

    } // 생성자 끝

} // Class 끝
