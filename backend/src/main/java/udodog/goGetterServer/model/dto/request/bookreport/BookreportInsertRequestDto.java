package udodog.goGetterServer.model.dto.request.bookreport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookreportInsertRequestDto {

    @NotEmpty private String bookName;                   // 책 제목
    @NotEmpty private String title;                      // 글 제목
    @NotEmpty private String content;                    // 글 내용
    private String bookReportTag;                        // 태그 내용

    @Builder
    public BookReport toEntity(BookreportInsertRequestDto insetRequestDto, Optional<User> user) {

        return BookReport.builder()
                .user(user.get())
                .bookName(insetRequestDto.bookName)
                .title(insetRequestDto.title)
                .content(insetRequestDto.content)
                .build();

    } // toEntity() 끝

} // Class 끝
