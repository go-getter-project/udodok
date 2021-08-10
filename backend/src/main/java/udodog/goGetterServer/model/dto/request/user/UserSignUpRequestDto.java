package udodog.goGetterServer.model.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestDto {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @Size(min = 4 ,max = 16, message = "아이디는 4글자 이상, 16글자이하 이여야 합니다.")
    @NotEmpty
    private String nickName;

    @NotEmpty
    private String phoneNumber;

    @Builder
    public User toEntity(){

        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .grade(UserGrade.USER)
                .build();

    }
}
