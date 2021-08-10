package udodog.goGetterServer.model.converter.nationwidelibraryinfo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.nationwidelibraryinfo.NationwideLibraryInfoController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.NationwidelibraryInfoResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NationwidelibraryInfoConverter implements RepresentationModelAssembler<DefaultRes<NationwidelibraryInfoResponseDto>, EntityModel<DefaultRes<NationwidelibraryInfoResponseDto>>> {

    @Override
    public EntityModel<DefaultRes<NationwidelibraryInfoResponseDto>> toModel(DefaultRes<NationwidelibraryInfoResponseDto> entity) {
        return EntityModel.of(entity, linkTo ( methodOn ( NationwideLibraryInfoController.class ).libraryInfoDetail(null)).withRel("detail"),
                                      linkTo ( methodOn ( NationwideLibraryInfoController.class ).insertLibraryInfo(null)).withRel("insert"),
                                      linkTo ( methodOn ( NationwideLibraryInfoController.class ).updateLibraryInfo(null,null)).withRel("update"));
    }
} // Class 끝
