package udodog.goGetterServer.model.dto.request.bookreport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookreportUpdateRequestDto {

    @NotEmpty private String bookName;      // 책 이름
    @NotEmpty private String content;       // 글 내용
    private String tagName;                 // 태그 내용

} // Class 끝
