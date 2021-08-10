package udodog.goGetterServer.model.converter;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.SampleController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.entity.User;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EntityToModelConvertor implements RepresentationModelAssembler<DefaultRes<User>, EntityModel<DefaultRes<User>>> {

    @Override
    public EntityModel<DefaultRes<User>> toModel(DefaultRes<User> defaultRes) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn(SampleController.class).getBook()).withSelfRel(),
                linkTo(methodOn(SampleController.class).getBook()).withRel("delete"));
    } //toModel()끝



}
