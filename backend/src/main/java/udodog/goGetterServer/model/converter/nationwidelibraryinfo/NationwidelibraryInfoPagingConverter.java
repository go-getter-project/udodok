package udodog.goGetterServer.model.converter.nationwidelibraryinfo;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.nationwidelibraryinfo.NationwideLibraryInfoController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.NationwidelibraryInfoResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NationwidelibraryInfoPagingConverter implements RepresentationModelAssembler<DefaultRes<Page<NationwidelibraryInfoResponseDto>>, EntityModel<DefaultRes<Page<NationwidelibraryInfoResponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<Page<NationwidelibraryInfoResponseDto>>> toModel(DefaultRes<Page<NationwidelibraryInfoResponseDto>> defaultRes) {
        return EntityModel.of(defaultRes, linkTo( methodOn ( NationwideLibraryInfoController.class ).totalLibraryInfoFindAll(null)).withRel("Find-All"));
    } // toModel() 끝
} // Class 끝
