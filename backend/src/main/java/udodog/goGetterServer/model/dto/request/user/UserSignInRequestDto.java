package udodog.goGetterServer.model.dto.request.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInRequestDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
