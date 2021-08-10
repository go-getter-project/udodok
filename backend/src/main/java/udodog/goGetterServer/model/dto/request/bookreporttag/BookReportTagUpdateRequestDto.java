package udodog.goGetterServer.model.dto.request.bookreporttag;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class BookReportTagUpdateRequestDto {

    @NotNull private String tagName;        // Tag 내용
} // Class 끝
