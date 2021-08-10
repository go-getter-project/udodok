package udodog.goGetterServer.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateSharingReplyRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.CreateSharingReplyRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SharingBoardReply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch =FetchType.LAZY)
    @JsonIgnore
    private SharingBoard sharingBoard;

    @Basic(fetch = FetchType.EAGER)
    private String comment;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public SharingBoardReply(User user, SharingBoard sharingBoard, String comment) {
        this.user = user;
        this.sharingBoard = sharingBoard;
        this.comment = comment;
    }

    public SharingBoardReply(CreateSharingReplyRequest request, User user, SharingBoard board) {
        this.comment = request.getComment();
        this.user = user;
        this.sharingBoard = board;

    }

    public SharingBoardReply updateBoard(UpdateSharingReplyRequest request) {
        this.comment = request.getComment();
        return this;
    }
}
