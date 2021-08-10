package udodog.goGetterServer.model.dto.response;

import lombok.Getter;

@Getter
public class ReplaceTokenResponseDto {

    private String accessToken;

    public ReplaceTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
