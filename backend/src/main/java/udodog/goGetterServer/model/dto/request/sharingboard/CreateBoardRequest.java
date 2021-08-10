package udodog.goGetterServer.model.dto.request.sharingboard;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {

    @NotEmpty
    @ApiModelProperty(value = "현재 로그인중인 사용자의 userId")
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String bookTitle;

    private String sharingBoardTag;

    public CreateBoardRequest(@NotEmpty Long userId, @NotEmpty String title, @NotEmpty String content, @NotEmpty String bookTitle, String sharingBoardTag) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.bookTitle = bookTitle;
        this.sharingBoardTag = sharingBoardTag;
    }
}
