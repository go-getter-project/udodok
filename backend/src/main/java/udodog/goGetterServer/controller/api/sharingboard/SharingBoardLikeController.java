package udodog.goGetterServer.controller.api.sharingboard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.sharingboard.LikeUserRequest;
import udodog.goGetterServer.service.sharingboard.SharingBoardLikeService;

@Api(tags = {"좋아요 기능 API"})
@RestController
@RequiredArgsConstructor
public class SharingBoardLikeController {

    private final SharingBoardLikeService sharingBoardLikeService;

    @ApiOperation(value = "공유 게시판 좋아요 API", notes = "공유 게시판 게시글 좋아요 기능에 사용되는 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "1.좋아요 등록 \t\n 2.좋아요 취소 \t\n 3.글이 존재하지 않음 \t\n 4.토큰 에러"),
    })
    @PostMapping("/api/users/like")
    public ResponseEntity<DefaultRes> sharingBoardLike(@RequestParam("boardId") Long boardId , @RequestBody LikeUserRequest request){
        return new ResponseEntity<>(sharingBoardLikeService.likeFeature(boardId,request.getUserId()), HttpStatus.OK);
    }
}
