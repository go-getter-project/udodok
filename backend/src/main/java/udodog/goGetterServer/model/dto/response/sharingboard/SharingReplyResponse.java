package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.SharingBoardReply;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class SharingReplyResponse {

    private WriterInfo writerInfo;

    private String comment;

    private LocalDateTime createdAt;

    private Long replyId;

    public SharingReplyResponse(SharingBoardReply sharingBoardReply, WriterInfo writerInfo) {
        this.writerInfo = writerInfo;
        this.comment = sharingBoardReply.getComment();
        this.createdAt = sharingBoardReply.getCreatedAt();
        this.replyId = sharingBoardReply.getId();
    }
}
