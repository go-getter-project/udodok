package udodog.goGetterServer.model.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@Getter
public class UserFindEmailResponseDto {

    private String email;

    public UserFindEmailResponseDto (Optional<UserFindEmailResponseDto> userEmail){
        this.email = userEmail.get().getEmail();
    }
}
