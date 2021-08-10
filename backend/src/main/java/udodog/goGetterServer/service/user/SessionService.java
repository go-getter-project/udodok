package udodog.goGetterServer.service.user;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.ReplaceTokenResponseDto;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.model.utils.JwtUtil;
import udodog.goGetterServer.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserRepository userRepository;

    public Boolean refreshTokenCheck(Long id, String refreshToken){
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> {
            if(user.getToken().equals(refreshToken)){
                return true;
            }else{
                return false;
            }
        }).orElse(false);
    }

    public Map<String, UserGrade> userGradeCheck(Long id){

        Map<String, UserGrade> result = new HashMap<>();
        result.put("permission", null);

        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.ifPresent(user -> {
            result.replace("permission", user.getGrade());
        });

        return result;
    }

    public DefaultRes replaceToken(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        if (token == null) {
            return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "요청에러");
        }

        String jwtToken = token.substring("Bearer ".length());
        Claims claims = JwtUtil.getClaims(jwtToken);
        if (claims == null){
            return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "토큰불일치");
        }

        String tokenName = claims.get("token_name", String.class);
        String userGrade = claims.get("user_grade", String.class);

        if(tokenName.equals(JwtUtil.REFRESH_TOKEN_NAME)){

            Long userPk = claims.get("user_pk", Long.class);
            Boolean check = refreshTokenCheck(userPk, jwtToken);
            if(check){
                String accessToken;
                if(userGrade.equals(UserGrade.ADMIN)){
                    accessToken = JwtUtil.createAccessToken(userPk, UserGrade.ADMIN);
                }else if(userGrade.equals(UserGrade.USER)){
                    accessToken = JwtUtil.createAccessToken(userPk, UserGrade.USER);
                }else {
                    accessToken = JwtUtil.createAccessToken(userPk, UserGrade.BLACK);
                }

                return DefaultRes.response(HttpStatus.OK.value(), "토큰재발급", new ReplaceTokenResponseDto(accessToken));
            }else{
                return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "토큰불일치");
            }

        }else{
            return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "토큰불일치");
        }
    }
}
