package udodog.goGetterServer.model.dto.response.user;

import lombok.Getter;

@Getter
public class JwtAccessTokenResponse {

    private String message;

    private String accessToken;

    public JwtAccessTokenResponse(String message, String accessToken) {
        this.message = message;
        this.accessToken = accessToken;
    }
}
