package udodog.goGetterServer.controller.api.oauth;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.oauth.OauthConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.enumclass.oauth.SocialLoginType;
import udodog.goGetterServer.service.oauth.OauthService;

@Api(tags = {"소셜로그인 관련 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Slf4j
public class OauthController {

    private final OauthService oauthService;
    private final OauthConverter oauthConverter;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     */
    @ApiOperation(value = "소셜로그인 API",notes = "소셜로그인 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 등록성공 \t\n 2. 토큰수정완료 \t\n 3. 등록수정실패 \t\n 4. 등록실패 \t\n 5. 데이터없음")
    })
    @ApiParam("GOOGLE, FACEBOOK")

    @GetMapping(value = "/{socialLoginType}")
    public ResponseEntity<EntityModel<DefaultRes>> socialLoginType(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);

        return new ResponseEntity<>(oauthConverter.toModel(oauthService.request(socialLoginType)), HttpStatus.OK);
    }

    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param socialLoginType (GOOGLE, FACEBOOK)
     * @param code API Server 로부터 넘어노는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @GetMapping(value = "/callback/{socialLoginType}")
    public ResponseEntity<EntityModel<DefaultRes>> callback(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        return new ResponseEntity<>(oauthConverter.toModel(oauthService.requestAccessToken(socialLoginType, code)), HttpStatus.OK);
    }
}
