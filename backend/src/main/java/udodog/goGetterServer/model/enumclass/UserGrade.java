package udodog.goGetterServer.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserGrade {

    BLACK("블랙회원"),
    USER("일반회원"),
    ADMIN("관리자")
    ;

    private String description;
}
