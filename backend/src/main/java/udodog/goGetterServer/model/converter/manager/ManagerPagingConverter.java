package udodog.goGetterServer.model.converter.manager;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.manager.UserManagementController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ManagerPagingConverter implements RepresentationModelAssembler<DefaultRes<Page<UserSearchResponseDto>>, EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<Page<UserSearchResponseDto>>> toModel(DefaultRes<Page<UserSearchResponseDto>> defaultRes) {
        return EntityModel.of(defaultRes, linkTo( methodOn ( UserManagementController.class ).totalMemberFindAll(null)).withRel("Find-All"),
                                          linkTo( methodOn( UserManagementController.class ).nameSearch(null, null)).withRel("nameSearch"),
                                          linkTo( methodOn( UserManagementController.class ).emailSearch(null, null)).withRel("emailSearch"),
                                          linkTo( methodOn( UserManagementController.class ).nickNameSearch(null, null)).withRel("nickNameSearch"),
                                          linkTo( methodOn( UserManagementController.class ).gradeSearch(null, null)).withRel("gradeSearch"));
    }
}
