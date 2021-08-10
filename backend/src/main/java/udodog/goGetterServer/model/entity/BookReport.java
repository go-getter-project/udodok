package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BookReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookReportId;
    
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    
    private String bookName;

    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public BookReport(User user, String title, String bookName, String content, LocalDateTime createdAt) {
        this.user = user;
        this.title = title;
        this.bookName = bookName;
        this.content = content;
        this.createdAt = createdAt;
    } // 생성자 끝
} // Class끝
