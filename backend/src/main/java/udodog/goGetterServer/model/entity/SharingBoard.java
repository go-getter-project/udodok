package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateBoardRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.CreateBoardRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @NoArgsConstructor
public class SharingBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    private User user;

    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    // Weekly Best 기능을 위해 추가
    private Integer likeCnt;

    //책 제목 추가
    private String bookTitle;

    @OneToMany(mappedBy = "sharingBoard" )
    private List<SharingBoardReply> sharingBoardReplyList = new LinkedList<>();


    @Builder
    public SharingBoard(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public SharingBoard(CreateBoardRequest request, Optional<User> user) {
        this.user = user.get();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.bookTitle = request.getBookTitle();
        this.likeCnt = 0;
    }

    public Integer getReplyCnt(){
        return this.sharingBoardReplyList.size();
    }

    public SharingBoard updateBoard(UpdateBoardRequest request) {

        this.title = request.getTitle();
        this.content = request.getContent();
        this.bookTitle = request.getBookTitle();

        return this;
    }

    public void minusLikeCnt() {
        this.likeCnt -=  1;
    }

    public void plusLikeCnt() {
        this.likeCnt +=  1;
    }
}
