package udodog.goGetterServer.controller.api.sharingboard;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import udodog.goGetterServer.config.WebMvcConfig;
import udodog.goGetterServer.model.converter.sharingboard.SharingConverter;
import udodog.goGetterServer.model.converter.sharingboard.SharingListConverter;
import udodog.goGetterServer.service.sharingboard.SharingBoardService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SharingBoardController.class)
public class SharingBoardControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private SharingBoardService sharingBoardService;

    @MockBean
    private SharingConverter sharingConverter;

    @MockBean
    private SharingListConverter sharingListConverter;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    @DisplayName("공유 게시판 전체 조회 테스트")
    public void 전체조회_테스트() throws Exception {
        mockMvc.perform(get("/api/sharings")
                .contentType(MediaTypes.ALPS_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }




}