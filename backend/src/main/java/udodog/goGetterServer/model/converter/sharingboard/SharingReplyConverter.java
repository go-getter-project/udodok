package udodog.goGetterServer.model.converter.sharingboard;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.sharingboard.SharingReplyController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.sharingboard.SharingReplyResponse;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SharingReplyConverter implements RepresentationModelAssembler<DefaultRes<List<SharingReplyResponse>>
        , EntityModel<DefaultRes<List<SharingReplyResponse>>>> {
    @Override
    public EntityModel<DefaultRes<List<SharingReplyResponse>>> toModel(DefaultRes<List<SharingReplyResponse>> defaultRes) {

        return EntityModel.of(defaultRes,
                linkTo(methodOn(SharingReplyController.class).getReplyList(null,null)).withSelfRel(),
                linkTo(methodOn(SharingReplyController.class).createReply(null,null)).withRel("create"),
                linkTo(methodOn(SharingReplyController.class).updateReply(null,null)).withRel("update"),
                linkTo(methodOn(SharingReplyController.class).deleteReply(null,null)).withRel("delete"));
    }
}
