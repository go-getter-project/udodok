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
import udodog.goGetterServer.model.converter.discussion.DiscussionReplyListConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyEditRequest;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyInsertRequest;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.service.discussion.DiscussionReplyService;

import javax.validation.Valid;

@Api(tags = {"토론 게시판 댓글 관련 API"})
@RestController
@RequiredArgsConstructor
public class DiscussionReplyController {

    private final DiscussionReplyListConverter replyListConvertor;
    private final DiscussionReplyService replyService;

    @ApiOperation(value = "토론게시판 댓글 등록 API",notes = "댓글 등록 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 등록성공 \t\n 2. 등록실패 \t\n 3. 토큰에러")
    })

    // 댓글 등록 Controller
    @PostMapping("/api/users/discussionreplies/{discussionId}")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionReplyResponse>>> createReply(
            @ApiParam("필수 : 모든사항")
            @RequestBody DiscussionReplyInsertRequest requestDto,
            @PathVariable("discussionId") Long discussionId,
            @RequestParam("userId") Long userId){

        return new ResponseEntity<>(replyListConvertor.toModel(replyService.createReply(requestDto, discussionId, userId)), HttpStatus.OK);
    }


    @ApiOperation(value = "토론게시판 댓글 전체 목록 조회 API",notes = "댓글 전체 목록 조회 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 댓글 조회 Controller
    @GetMapping("/api/bkusers/discussionreplies/{discussionId}")
    public ResponseEntity<EntityModel<DefaultRes<Page<DiscussionReplyResponse>>>> getBoardReplyList(
            @PathVariable("discussionId") Long discussionId,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 3) Pageable pageable // 최신 날짜순으로 내림차순, 페이지당 3개씩 출력
    ){
        return new ResponseEntity<>(replyListConvertor.toModel(replyService.getBoardReplyList(discussionId, pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 댓글 수정 API",notes = "댓글 수정 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 수정성공 \t\n 2. 데이터없음 \t\n 3. 수정실패 \t\n 4. 토큰에러")
    })

    // 댓글 수정 Controller
    @PatchMapping("/api/users/discussionreplies/{discussionId}")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionReplyEditRequest>>> updateReply(
            @PathVariable("discussionId") Long discussionId,
            @RequestParam("replyId") Long replyId,
            @RequestParam("userId") Long userId,
            @Valid@RequestBody DiscussionReplyEditRequest requestDto
    ) {

        return new ResponseEntity<>(replyListConvertor.toModel(replyService.updateReply(requestDto, discussionId, replyId, userId)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 댓글삭제 API",notes = "댓글삭제 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.삭제성공 \t\n 2. 삭제실패 \t\n 3. 데이터없음 \t\n 4. 토큰에러")
    })

    // 댓글 삭제 Controller
    @DeleteMapping("/api/users/discussionreplies/{discussionId}")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionBoardReply>>> deleteReply (
            @PathVariable("discussionId") Long discussionId, @RequestParam("replyId") Long replyId,
            @RequestParam("userId") Long userId) {

        return new ResponseEntity<>(replyListConvertor.toModel(replyService.delete(discussionId, replyId, userId)), HttpStatus.OK);
    }
}
