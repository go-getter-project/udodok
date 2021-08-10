package udodog.goGetterServer.model.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserFindEmailRequest {

    String name;
    String phoneNumber;

}
