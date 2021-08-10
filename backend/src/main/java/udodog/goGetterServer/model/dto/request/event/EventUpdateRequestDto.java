package udodog.goGetterServer.model.dto.request.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventUpdateRequestDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String imgUrl;

    @NotNull
    private Long couponId;
}
