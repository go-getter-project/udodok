package udodog.goGetterServer.model.converter.event;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.event.EventController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.event.EventsResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventConverter implements RepresentationModelAssembler<DefaultRes<Page<EventsResponseDto>>, EntityModel<DefaultRes<Page<EventsResponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<Page<EventsResponseDto>>> toModel(DefaultRes<Page<EventsResponseDto>> entity){

        return EntityModel.of(entity,
                     linkTo(methodOn(EventController.class).eventCreate(null)).withRel("event-create"),
                     linkTo(methodOn(EventController.class).progressEventFindAll(null)).withRel("progressEvent-find-all"),
                     linkTo(methodOn(EventController.class).endEventFindAll(null)).withRel("endEvent-find-all"),
                     linkTo(methodOn(EventController.class).eventDetailFind(null)).withRel("event-Find-detail"),
                     linkTo(methodOn(EventController.class).eventUpdate(null, null)).withRel("event-update"),
                     linkTo(methodOn(EventController.class).eventDelete(null)).withRel("event-delete"));

    }

}
