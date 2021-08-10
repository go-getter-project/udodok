package udodog.goGetterServer.model.dto.response.message;

import lombok.Getter;
import udodog.goGetterServer.model.entity.Message;

import java.time.LocalDateTime;

@Getter
public class MessageSendResponseDto {

    private Long theOtherUserId;

    private String nickName;

    private String content;

    private LocalDateTime sendAt;

    private Long messageRoomId;

    public MessageSendResponseDto(Message message) {
        this.theOtherUserId = message.getUser().getId();
        this.nickName = message.getUser().getNickName();
        this.content = message.getContent();
        this.sendAt = message.getSendAt();
        this.messageRoomId = message.getMessageRoom().getId();
    }
}
