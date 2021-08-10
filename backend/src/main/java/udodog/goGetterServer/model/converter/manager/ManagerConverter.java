package udodog.goGetterServer.model.converter.manager;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.manager.UserManagementController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ManagerConverter implements RepresentationModelAssembler<DefaultRes<UserSearchResponseDto>, EntityModel<DefaultRes<UserSearchResponseDto>>> {

    @Override
    public EntityModel<DefaultRes<UserSearchResponseDto>> toModel(DefaultRes<UserSearchResponseDto> entity) {
        return EntityModel.of(entity, linkTo ( methodOn ( UserManagementController.class ).memberDetail(null)).withRel("detail"),
                                      linkTo ( methodOn( UserManagementController.class ).updateMemberGrade(null, null)).withRel("update"),
                                      linkTo ( methodOn( UserManagementController.class ).blackMemberWithdrawal(null, null)).withRel("withdrawal"));
    } // toModel() 끝
} // Class 끝
