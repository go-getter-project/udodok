package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @NoArgsConstructor
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = MessageRoom.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_room_id")
    private MessageRoom messageRoom;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @CreatedDate
    private LocalDateTime sendAt;

    private Boolean isChecked;

    private Boolean isDeleted;

    @Builder
    public Message(MessageRoom messageRoom, User user, String content) {
        this.messageRoom = messageRoom;
        this.user = user;
        this.content = content;
        this.isChecked = false;
        this.isDeleted = false;
    }


    public void checkMessage(){
        this.isChecked = true;
    }

    public void deleteMessage(){
        this.isDeleted = true;
    }
}
