package udodog.goGetterServer.model.converter.sharingboard;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.sharingboard.SharingBoardController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.sharingboard.BoardResponse;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SharingConverter implements RepresentationModelAssembler<DefaultRes<BoardResponse>
        , EntityModel<DefaultRes<BoardResponse>>> {

    @Override
    public EntityModel<DefaultRes<BoardResponse>> toModel(DefaultRes<BoardResponse> defaultRes) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn(SharingBoardController.class).getBoardDetail(null)).withSelfRel()
                ,linkTo(methodOn(SharingBoardController.class).getBoardList(null)).withRel("list")
                ,linkTo(methodOn(SharingBoardController.class).createSharingBoard(null)).withRel("create")
                ,linkTo(methodOn(SharingBoardController.class).updateSharingBoard(null,null)).withRel("update")
                ,linkTo(methodOn(SharingBoardController.class).deleteSharingBoard(null,null)).withRel("delete"));
    }


}
