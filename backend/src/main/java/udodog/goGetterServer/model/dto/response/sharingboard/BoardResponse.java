package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardReply;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class BoardResponse {

    private Long id;

    private WriterInfo writerInfo;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    // 댓글 수
    private Integer replyCnt;

    // 좋아요 수
    private Integer likeCnt;

    private List<SharingReplyResponse> sharingBoardReplyList = new LinkedList<>();

    // 책 제목
    private String bookTitle;

    private String tagContent;

    public BoardResponse(Optional<SharingBoard> sharingBoard, Integer replyCnt, Integer likeCnt, WriterInfo writerInfo, String content) {
        SharingBoard board = sharingBoard.get();

        this.id = board.getId();
        this.writerInfo = writerInfo;
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.bookTitle = board.getBookTitle();

        this.replyCnt = replyCnt;
        this.likeCnt = likeCnt;

        for (SharingBoardReply sharingBoardReply : board.getSharingBoardReplyList()){
            SharingReplyResponse sharingReplyResponse = new SharingReplyResponse(sharingBoardReply, writerInfo);
            this.sharingBoardReplyList.add(sharingReplyResponse);
        }

        this.tagContent = content;

    }

    public BoardResponse(Optional<SharingBoard> sharingBoard, Integer replyCnt, Integer likeCnt, WriterInfo writerInfo) {
        SharingBoard board = sharingBoard.get();

        this.id = board.getId();
        this.writerInfo = writerInfo;
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.bookTitle = board.getBookTitle();

        this.replyCnt = replyCnt;
        this.likeCnt = likeCnt;

        for (SharingBoardReply sharingBoardReply : board.getSharingBoardReplyList()){
            SharingReplyResponse sharingReplyResponse = new SharingReplyResponse(sharingBoardReply, writerInfo);
            this.sharingBoardReplyList.add(sharingReplyResponse);
        }

    }
}
