package udodog.goGetterServer.model.dto.response.message;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageFindAllResponseDto {

    private Long theOtherUserId;

    private String nickName;

    private String content;

    private LocalDateTime sendAt;

    private Long messageRoomId;

   // private Integer uncheckedMessageCnt;

    @Builder
    public MessageFindAllResponseDto(Long theOtherUserId, String nickName,
                                     String content, LocalDateTime sendAt, Long messageRoomId) {
        this.theOtherUserId = theOtherUserId;
        this.nickName = nickName;
        this.content = content;
        this.sendAt = sendAt;
        this.messageRoomId = messageRoomId;
    }
}
