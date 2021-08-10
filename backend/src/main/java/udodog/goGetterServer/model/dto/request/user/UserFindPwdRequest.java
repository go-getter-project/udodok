package udodog.goGetterServer.model.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserFindPwdRequest {

    String email;
    String name;
}
