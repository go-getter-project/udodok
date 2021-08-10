package udodog.goGetterServer.model.converter.oauth;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.oauth.OauthController;
import udodog.goGetterServer.model.dto.DefaultRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OauthConverter implements RepresentationModelAssembler<DefaultRes, EntityModel<DefaultRes>> {

    @Override
    public EntityModel<DefaultRes> toModel(DefaultRes entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(OauthController.class).socialLoginType(null)).withSelfRel(),
                linkTo(methodOn(OauthController.class).callback(null, null)).withSelfRel());
    }
}
