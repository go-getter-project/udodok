package udodog.goGetterServer.model.dto.request.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.enumclass.UserGrade;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class MemberUpdateGradeRequestDto {

    @NotNull private UserGrade userGrade;      // 회원 등급
} // Class 끝
