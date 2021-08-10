package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookReportDetailResponseDto {

    private String bookName;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private String bookReportTag;

    public BookReportDetailResponseDto(String bookName, String title, String content, LocalDateTime createAt, String bookReportTag) {
        this.bookName = bookName;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.bookReportTag = bookReportTag;
    }
} // Class 끝
