package udodog.goGetterServer.model.dto.request.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class DiscussionReplyInsertRequest {

    @NotNull
    private String content; // 댓글 내용

    public DiscussionBoardReply toEntity(Optional<DiscussionBoard> board, Optional<User> user, DiscussionReplyInsertRequest requestDto){
        return DiscussionBoardReply.builder()
                .discussionBoard(board.get())
                .user(user.get())
                .content(requestDto.content)
                .build();
    }

}
