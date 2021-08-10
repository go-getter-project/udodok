package udodog.goGetterServer.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;
import udodog.goGetterServer.config.WebMvcConfig;
import udodog.goGetterServer.model.converter.EntityToModelConvertor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SampleController.class)
class SampleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EntityToModelConvertor entityToModelConvertor;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void 디폴트_응답_테스트() throws Exception {

        mvc.perform(get("/sample")
                .contentType(MediaTypes.ALPS_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

    }

}