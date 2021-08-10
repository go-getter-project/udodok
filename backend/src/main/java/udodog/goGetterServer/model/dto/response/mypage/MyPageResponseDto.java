package udodog.goGetterServer.model.dto.response.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@Getter
public class MyPageResponseDto {

    private Long id;                // 유저 번호
    private String email;           // 이메일
    private String name;            // 이름
    private String phoneNumber;     // 핸드폰 번호
    private String nickName;        // 닉네임
    private String password;        // 비밀번호

    public MyPageResponseDto(Optional<MyPageResponseDto> userInfo) {

        this.id = userInfo.get().getId();
        this.email = userInfo.get().getEmail();
        this.name = userInfo.get().getName();
        this.phoneNumber = userInfo.get().getPhoneNumber();
        this.nickName = userInfo.get().getNickName();
        this.password = userInfo.get().getPassword();
    }
}
