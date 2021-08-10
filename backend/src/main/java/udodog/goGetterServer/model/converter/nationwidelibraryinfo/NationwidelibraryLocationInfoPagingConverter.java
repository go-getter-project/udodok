package udodog.goGetterServer.model.converter.nationwidelibraryinfo;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.nationwidelibraryinfo.NationwideLibraryInfoController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.visualization.NationwidelibraryInfoVisualizationDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NationwidelibraryLocationInfoPagingConverter implements RepresentationModelAssembler<DefaultRes<Page<NationwidelibraryInfoVisualizationDto>>, EntityModel<DefaultRes<Page<NationwidelibraryInfoVisualizationDto>>>> {

    @Override
    public EntityModel<DefaultRes<Page<NationwidelibraryInfoVisualizationDto>>> toModel(DefaultRes<Page<NationwidelibraryInfoVisualizationDto>> defaultRes) {
        return EntityModel.of(defaultRes, linkTo( methodOn (NationwideLibraryInfoController.class ).findLibraryLocation(null)).withRel("Find-Library-Location"));
    } // toModel() 끝
} // Class 끝
