package udodog.goGetterServer.controller.api.bookreport;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.bookreport.BookReportConverter;
import udodog.goGetterServer.model.converter.bookreport.BookReportPagingConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.bookreport.BookreportInsertRequestDto;
import udodog.goGetterServer.model.dto.request.bookreport.BookreportUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.bookreport.BookReportDetailResponseDto;
import udodog.goGetterServer.model.dto.response.bookreport.BookreportResponseDto;
import udodog.goGetterServer.service.bookreport.BookreportService;

import javax.validation.Valid;

@Api(tags = {"독서 기록 관련 API"})
@RestController
@RequiredArgsConstructor
public class BookReportController {

    private final BookReportPagingConverter bookReportPagingConverter;
    private final BookReportConverter bookReportConverter;
    private final BookreportService bookreportService;

    @ApiOperation( value = "독서 기록 전체 목록 조회 API", notes = "독서 기록의 전체 목록 조회 API" )
    @ApiResponses( value = { @ApiResponse( code=200, message = "1. 조회성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러" )})

    // 전체 조회 관련 Method
    @GetMapping("/api/bkusers/book-reports/{userId}")
    public ResponseEntity<EntityModel<DefaultRes<Page<BookreportResponseDto>>>> totalBookReportFindAll(
            @PageableDefault(size = 8) Pageable pageable,
            @PathVariable("userId") Long userId) {  // Index Value를 이용 최신 날짜순을 기준으로 내림차순으로 페이지당 10개씩 출력

        return new ResponseEntity<>(bookReportPagingConverter.toModel( bookreportService.searchBookReportList(pageable, userId)), HttpStatus.OK );
    } // totalBookReportFindAll() 끝




    @ApiOperation(value = "독서기록 상세 페이지 API", notes = "상세 페이지 API")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 상세보기성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 독서 기록 상세보기 관련 Method
    @GetMapping("/api/bkusers/book-reports/detail/{bookReportId}")
    public ResponseEntity<EntityModel<DefaultRes<BookReportDetailResponseDto>>> viewDetailBookReport(@PathVariable("bookReportId") Long bookReportId) {

        return new ResponseEntity<>(bookReportConverter.toModel(bookreportService.viewDetailBookReport(bookReportId)), HttpStatus.OK);

    } // viewDetailBookReport() 끝


    @ApiOperation(value = "독서 기록 글쓰기 API",notes = "글쓰기 API")
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.등록성공 \t\n 2.등록실패 \t\n 3. 토큰에러") })

    // 독서 기록 등록 관련 Mehotd
    @PostMapping("/api/bkusers/book-reports")
    public ResponseEntity<EntityModel<DefaultRes<BookreportInsertRequestDto>>> insertReport (
            @ApiParam( value = "필수 : Tag를 제외한 모든 항목" )
            @Valid @RequestBody BookreportInsertRequestDto bookreportInsertRequestDto, @RequestParam ( "userId" ) Long userId ) {

        return new ResponseEntity<>(bookReportConverter.toModel(bookreportService.insertReport(bookreportInsertRequestDto, userId)), HttpStatus.OK);
    } // insertReport() 끝

    @ApiOperation(value = "독서 기록 수정 API",notes = "독서 기록 수정 API")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "1.수정성공 \t\n 2.수정실패 \t\n 3.데이터없음 \t\n 4.토큰에러" )})

    // 수정 Method
    @PatchMapping("/api/bkusers/book-reports/{bookReportId}")
    public ResponseEntity<EntityModel<EntityModel<DefaultRes>>> updateBookReport (
            @Valid @RequestBody BookreportUpdateRequestDto updateRequestDto, @PathVariable("bookReportId") Long bookReportId, @RequestParam("userId") Long userId) {

        return new ResponseEntity<>(bookReportConverter.toModel(bookreportService.updateReport(updateRequestDto, bookReportId, userId)), HttpStatus.OK);
    } // updateBookReport() 끝

    @ApiOperation(value = "독서 기록 삭제 API",notes = "독서 기록 삭제 API")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "1.삭제성공 \t\n 2.삭제실패 \t\n 3.데이터없음 \t\n 4.토큰에러" )})

    // 삭제 Method
    @DeleteMapping("/api/bkusers/book-reports/{bookReportId}")
    public ResponseEntity<EntityModel<DefaultRes<BookReportDetailResponseDto>>> deleteReport (@PathVariable("bookReportId") Long bookReportId, @RequestParam("userId") Long userId) {

        return new ResponseEntity<>(bookReportConverter.toModel(bookreportService.deleteReport(bookReportId, userId)), HttpStatus.OK);

    } // deleteReport() 끝


    // ##################################### 검색 기능 #####################################

    @ApiOperation(value = "독서 기록 제목 검색 API",notes = "독서 기록 제목 검색 API")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "1.제목 검색성공 \t\n 2.제목 검색실패 \t\n 3.데이터없음 \t\n 4.토큰에러" )})

    // 제목 검색
    @GetMapping("/api/bkusers/book-reports/search/{title-search}")
    public ResponseEntity<EntityModel<DefaultRes<Page<BookreportResponseDto>>>> titleSearch ( @PathVariable("title-search") String title, @PageableDefault (sort = "bookReportId", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {

        return new ResponseEntity<>(bookReportPagingConverter.toModel(bookreportService.titleSearch(title, pageable)), HttpStatus.OK);

    } // titleSearch() 끝


} // Class 끝
