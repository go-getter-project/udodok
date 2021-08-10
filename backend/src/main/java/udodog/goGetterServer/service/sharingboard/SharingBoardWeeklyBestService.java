package udodog.goGetterServer.service.sharingboard;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.response.sharingboard.SimpleBoardResponse;
import udodog.goGetterServer.model.dto.response.sharingboard.WriterInfo;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.repository.SharingBoardRepository;
import udodog.goGetterServer.repository.querydsl.sharingboard.SharingBoardExtension;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SharingBoardWeeklyBestService {

    private final SharingBoardRepository sharingBoardRepository;

    public DefaultRes<List<SimpleBoardResponse>> getWeeklyBest() {
        List<SharingBoard> weeklyBest = sharingBoardRepository.getWeeklyBest();

        if (weeklyBest.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"데이터 없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"조회 성공", getSimpleBoardResponseList(weeklyBest));
    }

    @NotNull
    private List<SimpleBoardResponse> getSimpleBoardResponseList(List<SharingBoard> sharingBoardList) {
        List<SimpleBoardResponse> simpleBoardResponseList = new LinkedList<>();

        for(SharingBoard sharingBoard : sharingBoardList){
            Integer replyCnt = sharingBoard.getReplyCnt();
            Integer likeCnt = sharingBoard.getLikeCnt();

            WriterInfo writerInfo = WriterInfo.builder().nickName(sharingBoard.getUser().getNickName()).profileUrl(sharingBoard.getUser().getProfileUrl()).writerId(sharingBoard.getUser().getId()).build();

            SimpleBoardResponse simpleBoardResponse = new SimpleBoardResponse(sharingBoard, replyCnt, likeCnt, writerInfo);
            simpleBoardResponseList.add(simpleBoardResponse);
        }
        return simpleBoardResponseList;
    }
}
