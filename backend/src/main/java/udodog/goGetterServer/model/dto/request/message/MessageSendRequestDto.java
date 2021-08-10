package udodog.goGetterServer.model.dto.request.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.Message;
import udodog.goGetterServer.model.entity.MessageRoom;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSendRequestDto {

    @NotNull
    private Long messageRoomId;

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    @NotEmpty
    private String content;

    public Message toEntity(MessageRoom messageRoom, User user){
        return Message.builder()
                .messageRoom(messageRoom)
                .user(user)
                .content(content)
                .build();
    }

}
