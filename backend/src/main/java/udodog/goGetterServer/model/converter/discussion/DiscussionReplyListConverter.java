package udodog.goGetterServer.model.converter.discussion;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionReplyController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionReplyListConverter implements RepresentationModelAssembler<DefaultRes<Page<DiscussionReplyResponse>>,
        EntityModel<DefaultRes<Page<DiscussionReplyResponse>>>> {

    @Override
    public EntityModel<DefaultRes<Page<DiscussionReplyResponse>>> toModel(DefaultRes<Page<DiscussionReplyResponse>> entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(DiscussionReplyController.class).getBoardReplyList(null, null)).withRel("list"));
    }
}
