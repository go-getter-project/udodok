package udodog.goGetterServer.model.dto.request.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class MessageNotificationRequest {

    @NotNull
    @ApiModelProperty(value = "현재 로그인중인 사용자의 userId")
    private Long receiverId;
}
