package udodog.goGetterServer.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.service.user.SessionService;

import java.util.Map;


@Api(tags = {"auth 관련 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SessionController {

    private final SessionService sessionService;

    @ApiOperation(value = "회원 등급 체크",notes = "사용자 ID로 사용자 등급을 체크하는 API 입니다.")
    @GetMapping("/auth")
    public Map<String, UserGrade> userGradeCheck(
            @RequestParam("userId") Long id
    ){
        return sessionService.userGradeCheck(id);
    }

}
