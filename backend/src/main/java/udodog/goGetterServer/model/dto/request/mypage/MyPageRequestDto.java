package udodog.goGetterServer.model.dto.request.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MyPageRequestDto {

    String nickName;       // 닉네임
    String password;       // 비밀번호

}
