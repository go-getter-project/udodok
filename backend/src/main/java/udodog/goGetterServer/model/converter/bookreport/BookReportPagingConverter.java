package udodog.goGetterServer.model.converter.bookreport;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.bookreport.BookReportController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.bookreport.BookreportResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookReportPagingConverter implements RepresentationModelAssembler<DefaultRes<Page<BookreportResponseDto>>, EntityModel<DefaultRes<Page<BookreportResponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<Page<BookreportResponseDto>>> toModel(DefaultRes<Page<BookreportResponseDto>> defaultRes ) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn( BookReportController.class ).insertReport(null, null)).withRel("BookReport-Insert"),
                linkTo(methodOn( BookReportController.class ).viewDetailBookReport(null)).withRel("viewdetail"),
                linkTo(methodOn( BookReportController.class ).titleSearch(null, null)).withRel("titleSearch"),
                linkTo(methodOn( BookReportController.class ).totalBookReportFindAll(null, null)).withRel("Find-All"));

    } // toModel 끝
} // Class 끝
