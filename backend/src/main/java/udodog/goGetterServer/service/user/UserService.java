package udodog.goGetterServer.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.user.UserFindEmailRequest;
import udodog.goGetterServer.model.dto.request.user.UserFindPwdRequest;
import udodog.goGetterServer.model.dto.request.user.UserSignInRequestDto;
import udodog.goGetterServer.model.dto.request.user.UserSignUpRequestDto;
import udodog.goGetterServer.model.dto.response.user.UserFindEmailResponseDto;
import udodog.goGetterServer.model.dto.response.user.UserSignInResponseDto;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.utils.JwtUtil;
import udodog.goGetterServer.model.utils.MailHandler;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.UserQueryRepository;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String FROM_ADDRESS = "torvlf1@gmail.com"; // 본인 google 메일로 변경
    private final JavaMailSender mailSender;
    private final Executor executor;
    private final UserRepository userRepository;
    private final UserQueryRepository queryRepository;


    public DefaultRes emailConfirm(HttpServletRequest request, String email){

        Optional<String> optionalEmail = userRepository.findByEmail(email);

        return optionalEmail.map(e -> DefaultRes.response(HttpStatus.OK.value(), "이메일중복"))
                .orElseGet(()->{
                    CompletableFuture.runAsync(()->sendMail(request, email), executor);
                    return DefaultRes.response(HttpStatus.OK.value(), "전송성공");
                });

    }

    public void sendMail(HttpServletRequest request, String email){

        HttpSession session = request.getSession();

        String issuanceNum = "";

        for (int i = 0; i < 8; i++){
            issuanceNum += Integer.toString(new Random().nextInt(10));
        }
        session.setAttribute("issuanceNum", issuanceNum);
        session.setMaxInactiveInterval(5*60); //세션 만료 시간 5분

        String htmlContent = "<h2><b>안녕하세요&nbsp;</b></h2>" +
                "<h2><b>Go-getter 입니다.</b><h2>"+
                "<h3><br></h3><p><h2>본인확인을 위해 다음 인증 번호를 화면에 입력해주세요.</h2></p>" +
                "<p><h2><span style=\"background-color: rgb(255, 255, 0);\">"+issuanceNum+"</span><h2></p>" +
                "<h3><br></h3><p></p><p><h2>감사합니다.</h2></p>" +
                "<br><br><img src='cid:go-getter-img' width=90 height=90>";

        try{
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setFrom(FROM_ADDRESS);
            mailHandler.setSubject("[go-getter] 본인인증 확인");
            mailHandler.setText(htmlContent,true);
            mailHandler.setInline("go-getter-img","static/go-getter.jpg");

            mailHandler.send();

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

    }

    public DefaultRes issuanceConfirm(HttpServletRequest request, String number){
        HttpSession session = request.getSession();
        String issuanceNum = (String) session.getAttribute("issuanceNum");

        if (issuanceNum== null){

            return DefaultRes.response(HttpStatus.OK.value(), "불일치");

        }else if(issuanceNum.equals(number)){

            return DefaultRes.response(HttpStatus.OK.value(), "일치");

        }else {

            return DefaultRes.response(HttpStatus.OK.value(), "불일치");

        }


    }

    public DefaultRes signUp(UserSignUpRequestDto requestDto){

        String email = requestDto.getEmail();
        Optional<String> optionalEmail = userRepository.findByEmail(email);

        return optionalEmail.map(e -> DefaultRes.response(HttpStatus.OK.value(), "이메일중복"))
                .orElseGet(()->{
                    userRepository.save(requestDto.toEntity());
                    return DefaultRes.response(HttpStatus.OK.value(),"성공");
                });

    }

    @Transactional
    public DefaultRes<UserSignInResponseDto> signIn(UserSignInRequestDto requestDto){

        Optional<String> optionalEmail = userRepository.findByEmail(requestDto.getEmail());

        return optionalEmail.map(email ->{

            Optional<User> optionalUser = userRepository.findByUser(email, requestDto.getPassword());

            return optionalUser.map(user -> {
                String accessToken = JwtUtil.createAccessToken(user.getId(), user.getGrade());
                String refreshToken = JwtUtil.createRefreshToken(user.getId(), user.getGrade());
                user.setRefreshToken(refreshToken);

                return DefaultRes.response(HttpStatus.OK.value(),"성공", new UserSignInResponseDto(accessToken,refreshToken, user.getId(), user.getGrade(), user.getNickName()));

            }).orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "비밀번호불일치"));

        }).orElseGet(()->DefaultRes.response(HttpStatus.OK.value(), "아이디불일치"));

    }

    public DefaultRes findEmail(UserFindEmailRequest requestDto) {
        Optional<UserFindEmailResponseDto> userEmail = queryRepository.findByUser(requestDto);

        if (userEmail.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return userEmail.map(email -> {
            return DefaultRes.response(HttpStatus.OK.value(), "이메일찾기성공", new UserFindEmailResponseDto(userEmail));
        }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "이메일찾기실패"));
    }

    public DefaultRes findPassword(UserFindPwdRequest findPwd, HttpServletRequest request) {

        Optional<String> checkMail = userRepository.findByEmail(findPwd.getEmail());
        Optional<String> checkname = userRepository.findByEmail(findPwd.getName());

        if(checkMail.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
       }

        return checkMail.filter(email -> email.equals(findPwd.getEmail()))
                .map(findMail -> {
                    checkname.filter(name -> name.equals(findPwd.getName()));

                    CompletableFuture.runAsync(()-> sendPwdMail(findPwd, request), executor);



                    return DefaultRes.response(HttpStatus.OK.value(), "메일전송성공");
        }).orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "메일전송실패"));

    }

    private void sendPwdMail(UserFindPwdRequest findPwd, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String email = findPwd.getEmail();
        String pw = "";
        for (int i = 0; i < 12; i++){
            pw += (char)((Math.random() * 26 ) + 97);
        }

        queryRepository.updatePwd(email, pw);
        session.setAttribute("findPwd", pw);
        session.setMaxInactiveInterval(5*60);

        String htmlContent = "<h2><b>안녕하세요&nbsp;</b></h2>" +
                "<h2><b>Go-getter 입니다.</b><h2>"+
                "<h3><br></h3><p><h2> 임시 비밀번호 입니다. </h2></p>" +
                "<p><h2><span style=\"background-color: rgb(255, 255, 0);\">"+ pw +"</span><h2></p>" +
                "<h3><br></h3><p></p><p><h2>감사합니다.</h2></p>" +
                "<br><br><img src='cid:go-getter-img' width=90 height=90>";

        try{
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setFrom(FROM_ADDRESS);
            mailHandler.setSubject("[go-getter] 임시비밀번호 입니다.");
            mailHandler.setText(htmlContent,true);
            mailHandler.setInline("go-getter-img","static/go-getter.jpg");

            mailHandler.send();

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

    }


}
