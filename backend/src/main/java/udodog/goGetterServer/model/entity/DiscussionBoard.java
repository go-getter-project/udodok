package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionEditRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DiscussionBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createAt;

    @Builder
    public DiscussionBoard(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public DiscussionBoard updateBoard (DiscussionEditRequest request){

        if(this.title != request.getTitle()){
            this.title = request.getTitle();
        }

        if (this.content != request.getContent()){
            this.content = request.getContent();
        }

        return this;
    }
}
