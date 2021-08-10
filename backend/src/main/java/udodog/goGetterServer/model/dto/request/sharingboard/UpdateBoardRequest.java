package udodog.goGetterServer.model.dto.request.sharingboard;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
public class UpdateBoardRequest {

    @NotEmpty
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String bookTitle;

    private String sharingBoardTag;

}
