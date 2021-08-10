package udodog.goGetterServer.model.converter.discussion;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionListConverter implements RepresentationModelAssembler<DefaultRes<Page<DiscussionResponseDto>>,
        EntityModel<DefaultRes<Page<DiscussionResponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<Page<DiscussionResponseDto>>> toModel(DefaultRes<Page<DiscussionResponseDto>>defaultRes) {

        return EntityModel.of(defaultRes,
                linkTo(methodOn(DiscussionController.class).getBoardList(null)).withRel("list"),
                linkTo(methodOn(DiscussionController.class).getDetailBoard(null, null)).withRel("detail"),
                linkTo(methodOn(DiscussionController.class).searchTitle(null, null)).withRel("searchTitle"),
                linkTo(methodOn(DiscussionController.class).searchContent(null, null)).withRel("searchContent"),
                linkTo(methodOn(DiscussionController.class).searchAll(null, null)).withRel("searchAll"));
    }

}