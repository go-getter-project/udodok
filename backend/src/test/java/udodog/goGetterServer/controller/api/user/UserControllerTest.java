package udodog.goGetterServer.controller.api.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import udodog.goGetterServer.config.WebMvcConfig;
import udodog.goGetterServer.model.converter.user.UserConverter;
import udodog.goGetterServer.service.user.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void 이메일_전송() throws Exception {

        String email = "sample@sample.com";
        mvc.perform(get("/api/email-confirm").param("email",email))
                .andExpect(status().isOk());

    }

    @Test
    public void 발급번호_확인() throws Exception {

        String number = "12345678";
        mvc.perform(get("/api/issuance-confirm").param("number",number))
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입() throws Exception {

        mvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\": \"test22@test.com\",\n" +
                        "  \"name\": \"변현우22\",\n" +
                        "  \"nick_name\": \"woo00oo22\",\n" +
                        "  \"password\": \"test1234\",\n" +
                        "  \"phone_number\": \"010-9245-7396\"\n" +
                        "}"))
                .andExpect(status().isOk());

    }

    @Test
    public void 로그인() throws Exception {

        mvc.perform(post("/api/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "  \"email\": \"96woo94@naver.com\",\n" +
                    "  \"password\": \"test1234\"\n" +
                    "}"))

                .andExpect(status().isOk());
    }

}