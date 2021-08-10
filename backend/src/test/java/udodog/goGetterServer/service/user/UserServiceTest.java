package udodog.goGetterServer.service.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.user.UserSignInRequestDto;
import udodog.goGetterServer.model.dto.request.user.UserSignUpRequestDto;
import udodog.goGetterServer.model.dto.response.user.UserSignInResponseDto;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.model.utils.JwtUtil;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.UserQueryRepository;

import java.util.Optional;
import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@Slf4j
public class UserServiceTest {

    UserService userService;
    MockHttpServletRequest httpServletRequest;
    MockHttpSession mockHttpSession;

    @Mock
    UserRepository userRepository;

    @Mock
    UserQueryRepository userQueryRepository;

    @Mock
    JavaMailSender mailSender;

    @Mock
    Executor executor;

    @Mock
    JwtUtil jwtUtil;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockHttpSession = new MockHttpSession();
        httpServletRequest = new MockHttpServletRequest();
        jwtUtil = new JwtUtil("HONGPARKKIMSEOPARKBYEONGOGETTER!");
        userService = new UserService(mailSender, executor, userRepository, userQueryRepository);
    }

    @After
    public void clean(){
        mockHttpSession.clearAttributes();
    }

    @Test
    public void 이메일_중복_체크(){
        //when
        String email = "hwoo00oo96@gmail.com";

        //given
        DefaultRes defaultRes = userService.emailConfirm(httpServletRequest, email);

        //then
        assertThat(defaultRes.getMessage()).isEqualTo("전송성공");
    }

    @Test
    public void 발급번호_확인(){

        String number = "12345678";

        mockHttpSession.setAttribute("issuanceNum", number);

        httpServletRequest.setSession(mockHttpSession);

        DefaultRes defaultRes = userService.issuanceConfirm(httpServletRequest, number);

        assertThat(defaultRes.getMessage()).isEqualTo("일치");
    }

    @Test
    public void 회원가입(){

        //given
        String email = "96woo94@naver.com";
        String password = "1234";
        String name = "변현우";
        String nickName = "woo00oo";
        String phoneNumber = "010-1111-2222";
        UserSignUpRequestDto userSignUpRequestDto = new UserSignUpRequestDto(email,password,name,nickName,phoneNumber);

        User mockUser = User.builder()
                .email(userSignUpRequestDto.getEmail())
                .password(userSignUpRequestDto.getPassword())
                .name(userSignUpRequestDto.getName())
                .nickName(userSignUpRequestDto.getNickName())
                .phoneNumber(userSignUpRequestDto.getPhoneNumber())
                .grade(UserGrade.USER)
                .build();

        //when
        given(userRepository.save(any())).willReturn(mockUser);
        DefaultRes result = userService.signUp(userSignUpRequestDto);

        //given
        assertThat(result.getMessage()).isEqualTo("성공");
    }

    @Test
    public void 로그인(){

        //given
        String email = "96woo94@naver.com";
        String password = "test1234";
        String name = "변현우";
        String nickName = "woo00oo";
        String phoneNumber = "010-1111-2222";

        User mockUser = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .grade(UserGrade.USER)
                .build();

        UserSignInRequestDto userSignInRequestDto = new UserSignInRequestDto(email, password);

        //when
        given(userRepository.findByEmail(email)).willReturn(Optional.of(email));
        given(userRepository.findByUser(email, password)).willReturn(Optional.of(mockUser));
        DefaultRes<UserSignInResponseDto> res = userService.signIn(userSignInRequestDto);

        //then
        assertThat(res.getMessage()).isEqualTo("성공");

    }

}