package udodog.goGetterServer.controller.api.discussion;

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
import udodog.goGetterServer.model.converter.discussion.DiscussionConverter;
import udodog.goGetterServer.model.converter.discussion.DiscussionListConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionEditRequest;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionInsertRequestDto;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionDetailResponse;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionResponseDto;
import udodog.goGetterServer.service.discussion.DiscussionService;

import javax.validation.Valid;

@Api(tags = {"토론 게시판 관련 API"})
@RestController
@RequiredArgsConstructor
public class DiscussionController {

    private final DiscussionListConverter discussionListConvertor;
    private final DiscussionConverter discussionConvertor;
    private final DiscussionService discussionService;

    @ApiOperation(value = "토론게시판 전체 목록 조회 API",notes = "전체 목록 조회 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 전체 조회 Controller
    @GetMapping("/api/discussions")
    public ResponseEntity<EntityModel<DefaultRes<Page<DiscussionResponseDto>>>> getBoardList(
            @PageableDefault(sort = "discussionId", direction = Sort.Direction.DESC, size = 7) Pageable pageable) {
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.getBoardList(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 상세 페이지 API",notes = "상세 페이지 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 상세보기성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 상세보기 Controller
    @GetMapping("/api/bkusers/discussions/{id}")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionDetailResponse>>> getDetailBoard(@PathVariable("id") Long id,
                                                                                            @RequestParam Long userId) {
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.getDetailBoard(id, userId)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 글쓰기 API",notes = "글쓰기 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.등록성공 \t\n 2.등록실패 \t\n 3. 토큰에러")
    })

    // 글등록 Controller
    @PostMapping("/api/users/discussions/{userId}")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionInsertRequestDto>>> insertBoard(
            @ApiParam(value = "필수 : 모든 항목" )
            @Valid@RequestBody DiscussionInsertRequestDto requestDto,
            @PathVariable("userId") Long userId) {

        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.insertBoard(requestDto, userId)), HttpStatus.OK);

    }

    @ApiOperation(value = "토론게시판 글수정 API",notes = "글수정 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "1.수정성공 \t\n 2.수정실패 \t\n 3.데이터없음 \t\n 4.토큰에러")
    })

    // 글 업데이트 Controller
    @PatchMapping("/api/users/discussions/edit/{id}")
    public ResponseEntity<EntityModel<DefaultRes>> updateBoard(
            @Valid@RequestBody DiscussionEditRequest updatetRequestDto, @PathVariable("id") Long id,
            @RequestParam("userId") Long userId) {

        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.updateBoard(updatetRequestDto, id, userId)), HttpStatus.OK);

    }

    @ApiOperation(value = "토론게시판 글삭제 API",notes = "글삭제 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.삭제성공 \t\n 2. 삭제실패 \t\n 3. 데이터없음 \t\n 4. 토큰에러")
    })

    // 글 삭제 Controller
    @DeleteMapping("/api/users/discussions/del/{id}")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionDetailResponse>>> deleteBoard (@PathVariable("id") Long id,
                                                                                          @RequestParam("userId") Long userId)
    {

        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.delete(id, userId)), HttpStatus.OK);

    }

    @ApiOperation(value = "토론게시판 제목 검색 API",notes = "제목 검색 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.검색성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 제목으로 검색
    @GetMapping("/api/discussions/search-title/{title}")
    public ResponseEntity<EntityModel<DefaultRes<Page<DiscussionResponseDto>>>> searchTitle (@PathVariable("title") String title,
                                                                                             @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 7) Pageable pageable)
    {
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.searchTitle(title, pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 내용 검색 API",notes = "내용 검색 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.검색성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 내용으로 검색
    @GetMapping("/api/discussions/search-content/{content}")
    public ResponseEntity<EntityModel<DefaultRes<Page<DiscussionResponseDto>>>> searchContent (@PathVariable("content") String content,
                                                                                               @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 7) Pageable pageable){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.searchContent(content, pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 제목+내용 검색 API",notes = "제목+내용 검색 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.검색성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 제목 + 내용으로 검색
    @GetMapping("/api/discussions/search-all/{search}")
    public ResponseEntity<EntityModel<DefaultRes<Page<DiscussionResponseDto>>>> searchAll (@PathVariable("search") String search,
                                                                                           @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 7) Pageable pageable){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.searchAll(search, pageable)), HttpStatus.OK);
    }

}
