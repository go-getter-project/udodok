package udodog.goGetterServer.model.converter.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.user.UserController;
import udodog.goGetterServer.model.dto.DefaultRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserConverter implements RepresentationModelAssembler<DefaultRes, EntityModel<DefaultRes>> {

    @Override
    public EntityModel<DefaultRes> toModel(DefaultRes entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).emailConfirm(null,null)).withRel("email-confirm"),
                linkTo(methodOn(UserController.class).issuanceConfirm(null,null)).withRel("issuance-confirm"),
                linkTo(methodOn(UserController.class).signUp(null)).withRel("sign-up"),
                linkTo(methodOn(UserController.class).signin(null)).withRel("sign-in"),
                linkTo(methodOn(UserController.class).findEmail(null)).withRel("findEmail"),
                linkTo(methodOn(UserController.class).findPwd(null,null)).withRel("findPwd"));
    }

}
