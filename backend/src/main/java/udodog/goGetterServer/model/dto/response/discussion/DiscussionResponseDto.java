package udodog.goGetterServer.model.dto.response.discussion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiscussionResponseDto {

    private Long id;                // 게시판 번호
    private String userNickname;    // 유저 닉네임
    private String title;           // 게시판 제목
    private LocalDateTime createAt;     // 게시판 등록일

    private Integer readHit;        // 조회수

}