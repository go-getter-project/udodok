package udodog.goGetterServer.controller.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.model.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserApiInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        if (token == null) {
            response.sendError(401,"요청에러");
            return false;
        }

        String jwtToken = token.substring("Bearer ".length());
        Claims claims = JwtUtil.getClaims(jwtToken);
        if (claims == null){
            response.sendError(401,"엑세스토큰 불일치");
            return false;
        }

        String tokenName = claims.get("token_name", String.class);
        String userGrade = claims.get("user_grade", String.class);

        if (userGrade.equals(UserGrade.BLACK.name())){
            response.sendError(403,"권한없음");
            return false;
        }

        if(tokenName.equals(JwtUtil.ACCESS_TOKEN_NAME)){
            return true;
        }else{
            response.sendError(401,"토큰에러");
            return false;
        }

    }

}
