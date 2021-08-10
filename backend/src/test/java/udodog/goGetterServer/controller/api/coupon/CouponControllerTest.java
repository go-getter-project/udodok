package udodog.goGetterServer.controller.api.coupon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import udodog.goGetterServer.config.WebMvcConfig;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.service.coupon.CouponService;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponController.class)
public class CouponControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CouponService couponService;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void 쿠폰_등록() throws Exception {

        mvc.perform(post("/api/admin/coupons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"discount\": 30,\n" +
                        "  \"name\": \"쿠폰\",\n" +
                        "  \"quantity\": 50,\n" +
                        "  \"valid_date\": 30\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void 쿠폰_다운로드() throws Exception{
        Long userId = 1L;
        Long couponId = 1L;

        DefaultRes mockDefaultRes = new DefaultRes(200,"다운로드성공", null, null);
        given(couponService.couponDownload(userId, couponId)).willReturn(mockDefaultRes);

        mvc.perform(get("/api/users/coupons")
                .param("couponId", String.valueOf(couponId))
                .param("userId", String.valueOf(userId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_code", equalTo(200)))
                .andExpect(jsonPath("$.message", equalTo("다운로드성공")));
    }

}