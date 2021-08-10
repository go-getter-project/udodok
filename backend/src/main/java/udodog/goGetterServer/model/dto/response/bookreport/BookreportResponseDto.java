package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookreportResponseDto {

    private Long bookReportId;                      // 독서 기록 Index 번호
    private String bookName;                        // 책 제목
    private String title;                           // 독서 기록 글 제목
    private LocalDateTime createAt;                     // 작성일
    private String bookReportTag;                    // 태그 내용

    public BookreportResponseDto(Long bookReportId, String bookName, String title, LocalDateTime createAt, String bookReportTag) {
        this.bookReportId = bookReportId;
        this.bookName = bookName;
        this.title = title;
        this.createAt = createAt;
        this.bookReportTag = bookReportTag;
    }
} // Class 끝
