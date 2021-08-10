package udodog.goGetterServer.model.converter.manager;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.manager.UserManagementController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.model.dto.response.manager.visuallization.MemberJoinVisuallizationResponseDto;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ManagerListConverter implements RepresentationModelAssembler<DefaultRes<List<MemberJoinVisuallizationResponseDto>>, EntityModel<DefaultRes<List<MemberJoinVisuallizationResponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<List<MemberJoinVisuallizationResponseDto>>> toModel(DefaultRes<List<MemberJoinVisuallizationResponseDto>> entity) {
        return EntityModel.of(entity,
                linkTo ( methodOn ( UserManagementController.class ).memberJoingCount(null)).withSelfRel());
    } // toModel() 끝
} // Class 끝
