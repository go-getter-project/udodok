package udodog.goGetterServer.model.dto.response.message;

import lombok.Getter;

@Getter
public class MessageRoomResponseDto {

    private Long messageRoomId;

    public MessageRoomResponseDto(Long messageRoomId) {
        this.messageRoomId = messageRoomId;
    }
}
