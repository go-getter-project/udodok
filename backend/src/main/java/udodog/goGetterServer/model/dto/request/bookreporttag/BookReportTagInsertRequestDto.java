package udodog.goGetterServer.model.dto.request.bookreporttag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.model.entity.User;

import java.util.Optional;

@NoArgsConstructor
@Getter
public class BookReportTagInsertRequestDto {

    private String tagName;         // Tag 내용
    
    public BookReportTag toEntity(Optional<BookReport> bookReport, BookReportTagInsertRequestDto bookReportTagInsertRequestDto) {
        
        return BookReportTag.builder()
                .bookReport(bookReport.get())
                .tagName(bookReportTagInsertRequestDto.tagName)
                .build();
        
    } // toEntiry() 끝

} // Class 끝
