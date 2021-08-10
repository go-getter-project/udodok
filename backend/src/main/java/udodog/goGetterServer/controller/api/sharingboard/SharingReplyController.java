package udodog.goGetterServer.controller.api.sharingboard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.sharingboard.SharingReplyConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateSharingReplyRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.CreateSharingReplyRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.DeleteSharingReplyRequest;
import udodog.goGetterServer.model.dto.response.sharingboard.SharingReplyResponse;
import udodog.goGetterServer.service.sharingboard.SharingReplyService;

import java.util.List;

@Api(tags = {"공유 게시판 댓글 API"})
@RestController
@RequiredArgsConstructor
public class SharingReplyController {

    private final SharingReplyService replyService;
    private final SharingReplyConverter sharingReplyConverter;

    // 댓글 목록 조회
    @ApiOperation(value = "공유 게시판 댓글 목록 조회 API",notes = "댓글 목록 조회 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })
    @GetMapping("/api/users/sharing-reply")
    public ResponseEntity<EntityModel<DefaultRes<List<SharingReplyResponse>>>> getReplyList(
            @RequestParam("boardId") Long boardId,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ){
        return new ResponseEntity<>(sharingReplyConverter.toModel(replyService.getReplyList(boardId, pageable)), HttpStatus.OK);
    }

    // 댓글 등록
    @ApiOperation(value = "공유 게시판 댓글 등록 API",notes = "댓글 등록 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 댓글 등록 성공 \t\n 2. 댓글 등록 실패 \t\n 3. 토큰에러")
    })
    @PostMapping("/api/users/sharing-reply")
    public ResponseEntity<DefaultRes> createReply(@RequestParam("boardId") Long boardId, @RequestBody CreateSharingReplyRequest request){
        return new ResponseEntity<>(replyService.createReply(boardId, request), HttpStatus.OK);
    }

    // 댓글 수정
    @ApiOperation(value = "공유 게시판 댓글 수정 API",notes = "댓글 수정 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 댓글 수정 성공 \t\n 2. 댓글 수정 실패 \t\n 3. 댓글이 존재하지 않음 \t\n 4. 글이 존재하지 않음 \t\n 5. 토큰에러")
    })
    @PatchMapping("/api/users/sharing-reply")
    public ResponseEntity<DefaultRes> updateReply(@RequestParam("boardId") Long boardId, @RequestBody UpdateSharingReplyRequest request){
        return new ResponseEntity<>(replyService.updateReply(boardId, request), HttpStatus.OK);
    }

    // 댓글 삭제
    @ApiOperation(value = "공유 게시판 댓글 삭제 API", notes = "공유 게시판 댓글 삭제 시 사용되는 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "1.댓글 삭제 성공 \t\n 2.댓글 삭제 실패 \t\n 3.댓글이 존재하지 않음 \t\n 4. 글이 존재하지 않음 \t\n 5.토큰 에러"),
    })
    @DeleteMapping("/api/users/sharing-reply")
    public ResponseEntity<DefaultRes> deleteReply(@RequestParam("boardId") Long boardId, @RequestBody DeleteSharingReplyRequest request){
        return new ResponseEntity<>(replyService.deleteReply(boardId, request), HttpStatus.OK);
    }





}
