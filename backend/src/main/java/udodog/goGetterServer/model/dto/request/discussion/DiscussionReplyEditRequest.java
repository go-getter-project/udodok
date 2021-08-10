package udodog.goGetterServer.model.dto.request.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DiscussionReplyEditRequest {

    @NotNull
    private String content;     // 댓글 내용

    private LocalDateTime createAt = LocalDateTime.now();         // 댓글 수정일

}
