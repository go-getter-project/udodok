package udodog.goGetterServer.model.converter.bookreport;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.bookreport.BookReportController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.bookreport.BookReportDetailResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookReportConverter implements RepresentationModelAssembler<DefaultRes<BookReportDetailResponseDto>, EntityModel<DefaultRes<BookReportDetailResponseDto>>> {
    @Override
    public EntityModel<DefaultRes<BookReportDetailResponseDto>> toModel(DefaultRes<BookReportDetailResponseDto> entity) {
        return EntityModel.of(entity, linkTo( methodOn( BookReportController.class ).viewDetailBookReport( null)).withRel("detail"),
                                      linkTo( methodOn( BookReportController.class ).updateBookReport( null, null, null )).withRel("update"),
                                      linkTo( methodOn( BookReportController.class ).deleteReport( null, null )).withRel("delete"));
    } // toModel() 끝
} // Class 끝
