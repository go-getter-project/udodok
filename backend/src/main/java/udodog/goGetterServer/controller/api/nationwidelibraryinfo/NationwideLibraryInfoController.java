package udodog.goGetterServer.controller.api.nationwidelibraryinfo;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.nationwidelibraryinfo.NationwidelibraryInfoConverter;
import udodog.goGetterServer.model.converter.nationwidelibraryinfo.NationwidelibraryInfoPagingConverter;
import udodog.goGetterServer.model.converter.nationwidelibraryinfo.NationwidelibraryLocationInfoPagingConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.nationwidelibraryinfo.NationwideLibraryInfoInsertReqeustDto;
import udodog.goGetterServer.model.dto.request.nationwidelibraryinfo.NationwideLibraryInfoUpdateReqeustDto;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.NationwidelibraryInfoResponseDto;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.visualization.NationwidelibraryInfoVisualizationDto;
import udodog.goGetterServer.service.nationwidelibraryinfo.NationwidelibraryInfoService;

import javax.validation.Valid;

@Api(tags = {"전국 도서관 정보 관리 API"})
@RestController
@RequiredArgsConstructor
public class NationwideLibraryInfoController {

    private final NationwidelibraryInfoService nationwidelibraryInfoService;
    private final NationwidelibraryInfoConverter nationwidelibraryInfoConverter;
    private final NationwidelibraryInfoPagingConverter nationwidelibraryInfoPagingConverter;
    private final NationwidelibraryLocationInfoPagingConverter nationwidelibraryLocationInfoPagingConverter;

    @ApiOperation(value = "전국 도서관 정보 전체 조회 API", notes = "전국 도서관 정보 전체 목록 조회 API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "1. 조회 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})

    // 전체 조회 관련 Method
    @GetMapping("/api/library/list")
    public ResponseEntity<EntityModel<DefaultRes<Page<NationwidelibraryInfoResponseDto>>>> totalLibraryInfoFindAll (@PageableDefault(size = 10) Pageable pageable) { // 한 페이지 당 10개씩 출력
        return new ResponseEntity<>(nationwidelibraryInfoPagingConverter.toModel( nationwidelibraryInfoService.searchLibraryInfoList( pageable )), HttpStatus.OK);
    } // totalLibraryInfoFindAll() 끝

    @ApiOperation(value = "전국 도서관 정보 상세 조회 API", notes = "전국 도서관 정보 상세 조회 API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "1. 상세보기 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})

    // 도서관 정보 상세보기 관련 Method
    @GetMapping("/api/library/detail/{nationwideLibraryInfolibraryInfoId}")
    public ResponseEntity<EntityModel<DefaultRes<NationwidelibraryInfoResponseDto>>> libraryInfoDetail (@PathVariable("nationwideLibraryInfolibraryInfoId") Long nationwideLibraryInfolibraryInfoId ) {
        return new ResponseEntity<>(nationwidelibraryInfoConverter.toModel(nationwidelibraryInfoService.searchLibraryInfoDetail(nationwideLibraryInfolibraryInfoId)), HttpStatus.OK);
    } // libraryInfoDetail() 끝

    @ApiOperation(value = "전국 도서관 정보 입력 관련 API", notes = "전국 도서관 정보 입력 API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "1. 입력 성공 \t\n 2. 입력 실패 \t\n 3.Token Error")})

    // 도서관 정보 입력 관련 Method
    @PostMapping("/api/admin/library/inseart")
    public ResponseEntity<EntityModel<DefaultRes<NationwideLibraryInfoInsertReqeustDto>>> insertLibraryInfo (
            @ApiParam( value = "필수 : 도서관 이름 / 도시명 / 시, 군, 구 이름 / 도서관 유형 / 휴관일 / 평일 업무 시간 / 도로명 주소 / 위, 경도값 / 데이터 입력일자")
            @Valid @RequestBody NationwideLibraryInfoInsertReqeustDto infoInsertReqeustDto) {

        return new ResponseEntity<>(nationwidelibraryInfoConverter.toModel(nationwidelibraryInfoService.insertLibraryInfo(infoInsertReqeustDto)), HttpStatus.OK);
    } // insertLibraryInfo() 끝

    @ApiOperation(value = "전국 도서관 정보 수정 관련 API", notes = "전국 도서관 정보 수정 API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "1. 데이터 전달 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})

    // 전국 도서관 정보 수정 기능 관련 Method
    @PatchMapping("/api/admin/library/update/{nationwideLibraryInfolibraryInfoId}")
    public ResponseEntity<EntityModel<DefaultRes>> updateLibraryInfo (
            @Valid @RequestBody NationwideLibraryInfoUpdateReqeustDto updateReqeustDto, @PathVariable("nationwideLibraryInfolibraryInfoId") Long nationwideLibraryInfolibraryInfoId) {

        return new ResponseEntity<>(nationwidelibraryInfoConverter.toModel(nationwidelibraryInfoService.updateLibraryInfo(updateReqeustDto, nationwideLibraryInfolibraryInfoId)), HttpStatus.OK);
    } // updateLibraryInfo() 끝


//    @ApiOperation(value = "전국 도서관 정보 삭제 관련 API", notes = "전국 도서관 정보 삭제 관련 API")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "1. 데이터 전달 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})


    @ApiOperation(value = "전국 도서관 위치 정보 시각화 관련 API", notes = "전국 도서관 위치 정보 시각화 관련 API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "1. 데이터 전달 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})

    // 전국 데이터 위치 정보 시각화 관련 Method
    @GetMapping("/api/library/location-visualization")
    public ResponseEntity<EntityModel<DefaultRes<Page<NationwidelibraryInfoVisualizationDto>>>> findLibraryLocation (@PageableDefault(size = 100)Pageable pageable) { // 페이지당 100개씩 출력
        return new ResponseEntity<>(nationwidelibraryLocationInfoPagingConverter.toModel(nationwidelibraryInfoService.getLibraryLocationInfo( pageable )), HttpStatus.OK);
    } // findLibraryLocation() 끝
} // Class 끝
