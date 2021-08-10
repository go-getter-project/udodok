package udodog.goGetterServer.model.dto.response.message;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageFindDetailResponseDto {

    private String sendNickName;

    private String content;

    private LocalDateTime sendAt;

    @Builder
    public MessageFindDetailResponseDto(String sendNickName, String content, LocalDateTime sendAt) {
        this.sendNickName = sendNickName;
        this.content = content;
        this.sendAt = sendAt;
    }
}
