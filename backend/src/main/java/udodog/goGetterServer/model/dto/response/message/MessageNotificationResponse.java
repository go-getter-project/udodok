package udodog.goGetterServer.model.dto.response.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MessageNotificationResponse {

    Long uncheckedMessageCnt;

    public MessageNotificationResponse(Long uncheckedMessageCnt) {
        this.uncheckedMessageCnt = uncheckedMessageCnt;
    }
}
