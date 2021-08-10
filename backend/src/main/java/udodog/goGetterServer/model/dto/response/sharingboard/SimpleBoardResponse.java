package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.*;
import udodog.goGetterServer.model.entity.SharingBoard;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SimpleBoardResponse {

    private Long id;

    private WriterInfo writerInfo;

    private String title;

    private LocalDateTime createdAt;

    // 댓글 수
    private Integer replyCnt;

    // 좋아요 수
    private Integer likeCnt;

    //책 제목
    private String bookTitle;

    private String tagContent;

    public SimpleBoardResponse(SharingBoard sharingBoard, Integer replyCnt, Integer likeCnt, WriterInfo writerInfo) {
        this.id = sharingBoard.getId();
        this.writerInfo = writerInfo;
        this.title = sharingBoard.getTitle();
        this.createdAt = sharingBoard.getCreatedAt();
        this.bookTitle = sharingBoard.getBookTitle();

        this.replyCnt = replyCnt;
        this.likeCnt = likeCnt;
    }

    public SimpleBoardResponse(SharingBoard sharingBoard, Integer replyCnt, Integer likeCnt, WriterInfo writerInfo, String content) {
        this.id = sharingBoard.getId();
        this.writerInfo = writerInfo;
        this.title = sharingBoard.getTitle();
        this.createdAt = sharingBoard.getCreatedAt();
        this.bookTitle = sharingBoard.getBookTitle();

        this.replyCnt = replyCnt;
        this.likeCnt = likeCnt;
        this.tagContent = content;

    }
}
