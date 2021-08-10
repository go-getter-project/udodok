package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyEditRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DiscussionBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DiscussionBoard.class)
    @JoinColumn(name = "discussion_id")
    private DiscussionBoard discussionBoard;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
    private String content;

    @CreatedDate
    private LocalDateTime createAt;

    @Builder
    public DiscussionBoardReply(DiscussionBoard discussionBoard, User user, String content) {
        this.discussionBoard = discussionBoard;
        this.user = user;
        this.content = content;
    }

    public DiscussionBoardReply updateReply (DiscussionReplyEditRequest updateReply){

        if(this.content != updateReply.getContent()){
            this.content = updateReply.getContent();
        }

        return this;
    }
}
