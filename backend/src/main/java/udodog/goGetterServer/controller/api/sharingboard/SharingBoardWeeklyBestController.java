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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.sharingboard.SimpleBoardResponse;
import udodog.goGetterServer.service.sharingboard.SharingBoardWeeklyBestService;

import java.util.List;

@Api(tags = {"공유 게시판 인기글 조회 API"})
@RestController
@RequiredArgsConstructor
public class SharingBoardWeeklyBestController {

    private final SharingBoardWeeklyBestService sharingBoardWeeklyBestService;

    @ApiOperation(value = "공유 게시판 인기글 조회 API", notes = "공유 게시판 인기글 조회 시 사용되는 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "1.조회 성공 \t\n 2.데이터 없음 \t\n 3.토큰 에러"),
    })
    @GetMapping("/api/weekly-best")
    public ResponseEntity<DefaultRes<List<SimpleBoardResponse>>> getWeeklyBest(){
        return new ResponseEntity<>(sharingBoardWeeklyBestService.getWeeklyBest(), HttpStatus.OK);
    }
}
