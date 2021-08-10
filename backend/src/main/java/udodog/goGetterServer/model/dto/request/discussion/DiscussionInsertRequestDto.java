package udodog.goGetterServer.model.dto.request.discussion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiscussionInsertRequestDto {

    @NotNull
    private String title;       // 게시판 제목

    @NotNull
    private String content;     // 게시판 내용

    @Builder
    public DiscussionBoard toEntity(DiscussionInsertRequestDto create, Optional<User> user){
        return DiscussionBoard.builder()
                .user(user.get())
                .title(create.title)
                .content(create.content)
                .build();
    }
}
