package udodog.goGetterServer.service.oauth.social;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.user.UserSignInResponseDto;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.entity.UserConnection;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.model.enumclass.oauth.SocialLoginType;
import udodog.goGetterServer.model.utils.JwtUtil;
import udodog.goGetterServer.repository.UserConnectionRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.UserQueryRepository;
import udodog.goGetterServer.repository.querydsl.oauth.UserConnectionQueryRepository;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {

    @Value("${sns.google.url}")
    private String GOOGLE_SNS_BASE_URL;

    @Value("${sns.google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;

    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;

    @Value("${sns.google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;

    @Value("${sns.google.token.url}")
    private String GOOGLE_SNS_TOKEN_BASE_URL;

    private final UserConnectionRepository userConnectionRepository;
    private final UserConnectionQueryRepository userConnectionQueryRepository;
    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "profile email");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }

    @Override
    public String requestAccessToken(String code) {
        String access_Token = "";
        String reqURL = GOOGLE_SNS_TOKEN_BASE_URL;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + GOOGLE_SNS_CLIENT_ID);
            sb.append("&client_secret=" + GOOGLE_SNS_CLIENT_SECRET);
            sb.append("&redirect_uri=" + GOOGLE_SNS_CALLBACK_URL);
            sb.append("&code=" + code);
            sb.append("&state=url_parameter");
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                String result = "";
                while ((line = br.readLine()) != null) {
                    result += line;
                }
                //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(result);
                access_Token = element.getAsJsonObject().get("access_token").getAsString();

                br.close();
                bw.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
        }
        return access_Token;
    }

    public DefaultRes requestAccessTokenUsingURL(String access_token) {

        String reqURL = "https://www.googleapis.com/userinfo/v2/me?access_token=" + access_token;

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_token);
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                String result = "";
                while ((line = br.readLine()) != null) {
                    result += line;
                }

                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(result);

                String name = element.getAsJsonObject().get("name").getAsString();
                String email = element.getAsJsonObject().get("email").getAsString();
                String id = element.getAsJsonObject().get("id").getAsString();
                String picture = element.getAsJsonObject().get("picture").getAsString();

                Optional<String> socialEmail = userConnectionRepository.findByEmail(email);

                if (socialEmail.isEmpty()){
                    UserConnection userConnection =
                            UserConnection.builder()
                                    .email(email)
                                    .accessToken(access_token)
                                    .displayName(name)
                                    .provider(SocialLoginType.FACEBOOK)
                                    .providerId(id)
                                    .imageUrl(picture)
                                    .build();
                    System.out.println("userConnection: "+userConnection.toString());
                    userConnection = userConnectionRepository.save(userConnection);

                    User user = User.builder()
                            .email(userConnection.getEmail())
                            .password(userConnection.getAccessToken())
                            .name(userConnection.getDisplayName())
                            .nickName(userConnection.getDisplayName())
                            .grade(UserGrade.USER)
                            .userConnection(userConnection)
                            .build();

                    userRepository.save(user);

                    String accessToken = JwtUtil.createAccessToken(user.getId(), user.getGrade());
                    String refreshToken = JwtUtil.createRefreshToken(user.getId(), user.getGrade());
                    user.setRefreshToken(refreshToken);

                    return DefaultRes.response(HttpStatus.OK.value(), "등록성공", new UserSignInResponseDto(accessToken, refreshToken, user.getId(), user.getGrade(), user.getNickName()));

                }else{
                    userConnectionQueryRepository.updatePassword(email, access_token);
                    userQueryRepository.updatePassword(email, access_token);

                    User user = userQueryRepository.findBySocialEmail(email);

                    return DefaultRes.response(HttpStatus.OK.value(), "토큰수정완료", user);
                }
            }

        } catch (IOException e) {
            new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
        }

        return DefaultRes.response(HttpStatus.OK.value(), "등록수정실패");
    }
}