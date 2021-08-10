package udodog.goGetterServer.model.converter.mypage;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.mypage.MyPageController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.mypage.MyPageResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MyPageConverter implements RepresentationModelAssembler<DefaultRes<MyPageResponseDto>, EntityModel<DefaultRes<MyPageResponseDto>>> {

    @Override
    public EntityModel<DefaultRes<MyPageResponseDto>> toModel(DefaultRes<MyPageResponseDto> entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(MyPageController.class).getDetailUser(null)).withRel("detail"));
    }

}
