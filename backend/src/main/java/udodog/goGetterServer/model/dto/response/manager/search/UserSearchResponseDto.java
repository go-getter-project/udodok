package udodog.goGetterServer.model.dto.response.manager.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.enumclass.UserGrade;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserSearchResponseDto {

    private Long userId;                    // 회원 식별 번호
    private String email;                   // 회원 Email
    private String name;                    // 회원 이름
    private String phoneNumber;             // 회원 연락처
    private String nickName;                // 회원 별명
    private UserGrade userGrade;            // 회원 등급
    private LocalDate createdAt;            // 회원 가입일

    public UserSearchResponseDto(Long userId, String email, String name, String phoneNumber, String nickName, UserGrade userGrade, LocalDate createdAt) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.userGrade = userGrade;
        this.createdAt = createdAt;
    } // 생성자 끝
} // Class 끝
