package udodog.goGetterServer.service.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.enumclass.oauth.SocialLoginType;
import udodog.goGetterServer.service.oauth.social.FacebookOauth;
import udodog.goGetterServer.service.oauth.social.GoogleOauth;
import udodog.goGetterServer.service.oauth.social.SocialOauth;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final List<SocialOauth> socialOauthList;
    private final GoogleOauth googleOauth;
    private final FacebookOauth facebookOauth;

    public DefaultRes request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();

        return DefaultRes.response(HttpStatus.OK.value(), "리다이렉트주소", redirectURL);
    }

    public DefaultRes requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);

        if(socialLoginType == SocialLoginType.GOOGLE){
            String token = socialOauth.requestAccessToken(code);
            return DefaultRes.response(HttpStatus.OK.value(), "등록성공", googleOauth.requestAccessTokenUsingURL(token));
        }else if (socialLoginType == SocialLoginType.FACEBOOK){
            String token = socialOauth.requestAccessToken(code);
            return DefaultRes.response(HttpStatus.OK.value(), "등록성공", facebookOauth.requestAccessTokenUsingURL(token));
        }

        return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
}