package udodog.goGetterServer.service.sharingboard;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateBoardRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.CreateBoardRequest;
import udodog.goGetterServer.model.dto.response.sharingboard.BoardResponse;
import udodog.goGetterServer.model.dto.response.sharingboard.SimpleBoardResponse;
import udodog.goGetterServer.model.dto.response.sharingboard.WriterInfo;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardTag;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.SharingBoardReplyRepository;
import udodog.goGetterServer.repository.SharingBoardRepository;
import udodog.goGetterServer.repository.SharingBoardTagRepository;
import udodog.goGetterServer.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SharingBoardService {

    private final SharingBoardRepository sharingBoardRepository;
    private final SharingBoardReplyRepository sharingBoardReplyRepository;
    private final UserRepository userRepository;
    private final SharingBoardTagRepository sharingBoardTagRepository;

    // 전체 조회
    public DefaultRes<List<SimpleBoardResponse>> getBoardList(Pageable pageable) {
        Page<SharingBoard> sharingBoardList = sharingBoardRepository.findAll(pageable);

        if (sharingBoardList.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"데이터 없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"조회 성공",getSimpleBoardResponseList(sharingBoardList), new Pagination(sharingBoardList));
    }


    // 상세 조회
    public DefaultRes<BoardResponse> getBoardDetail(Long id) {
        Optional<SharingBoard> sharingBoard = sharingBoardRepository.findById(id);
        SharingBoardTag sharingBoardTag = sharingBoardTagRepository.findBySharingBoardId(id);

        if(sharingBoardTag != null) {
            return sharingBoard.map(board -> DefaultRes.response(HttpStatus.OK.value(), "조회 성공",
                    new BoardResponse(sharingBoard, board.getReplyCnt(), board.getLikeCnt(),
                            WriterInfo.builder().
                                    nickName(board.getUser().getNickName()).
                                    profileUrl(board.getUser().getProfileUrl()).writerId(board.getUser().getId()).build(), sharingBoardTag.getContent()))
            )
                    .orElseGet(() -> {
                        return DefaultRes.response(HttpStatus.OK.
                                value(), "데이터 없음");
                    });
        }
        else{
            return sharingBoard.map(board -> DefaultRes.response(HttpStatus.OK.value(), "조회 성공",
                    new BoardResponse(sharingBoard, board.getReplyCnt(), board.getLikeCnt(),
                            WriterInfo.builder().
                                    nickName(board.getUser().getNickName()).
                                    profileUrl(board.getUser().getProfileUrl()).writerId(board.getUser().getId()).build()))
            )
                    .orElseGet(() -> {
                        return DefaultRes.response(HttpStatus.OK.
                                value(), "데이터 없음");
                    });

        }
    }

    // 게시글 작성
    public DefaultRes createSharingBoard(CreateBoardRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        SharingBoard sharingBoard = new SharingBoard(request, user);
        SharingBoard saveBoard = sharingBoardRepository.save(sharingBoard);

        sharingBoardTagRepository.save(new SharingBoardTag(saveBoard.getId(),request.getSharingBoardTag()));

        return DefaultRes.response(HttpStatus.OK.value(),"글 등록 성공");

    }

    //게시글 수정
    @Transactional
    public DefaultRes<BoardResponse> updateSharingBoard(Long id, UpdateBoardRequest request) {
        Optional<SharingBoard> boardById = sharingBoardRepository.findById(id);

        if (boardById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글이 존재하지 않음");
        }

        SharingBoard updateBoard = boardById.get().updateBoard(request);
        SharingBoard saveBoard = sharingBoardRepository.save(updateBoard);

        // 태그 재등록
        sharingBoardTagRepository.deleteAllBySharingBoardId(id);
        sharingBoardTagRepository.save(new SharingBoardTag(saveBoard.getId(),request.getSharingBoardTag()));

        if(saveBoard.getId().equals(id)){
            return DefaultRes.response(HttpStatus.OK.value(),"글 수정 성공");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"글 수정 실패");
    }


    public DefaultRes deleteSharingBoard(Long boardId, Long UserId) {
        Optional<SharingBoard> boardById = sharingBoardRepository.findById(boardId);

        if (boardById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글이 존재하지 않음");
        }

        SharingBoard board = boardById.get();

        sharingBoardRepository.deleteById(boardId);

        return DefaultRes.response(HttpStatus.OK.value(),"글 삭제 성공");

    }

    @NotNull
    private List<SimpleBoardResponse> getSimpleBoardResponseList(Page<SharingBoard> sharingBoardList) {
        List<SimpleBoardResponse> simpleBoardResponseList = new LinkedList<>();

        for(SharingBoard sharingBoard : sharingBoardList){

            Integer replyCnt = sharingBoard.getReplyCnt();
            Integer likeCnt = sharingBoard.getLikeCnt();
            User user = sharingBoard.getUser();

            SharingBoardTag sharingBoardTag = sharingBoardTagRepository.findBySharingBoardId(sharingBoard.getId());
            WriterInfo writerInfo = WriterInfo.builder().nickName(user.getNickName()).profileUrl(user.getProfileUrl()).writerId(user.getId()).build();

            SimpleBoardResponse simpleBoardResponse;

            if(sharingBoardTag != null){
                simpleBoardResponse = new SimpleBoardResponse(sharingBoard, replyCnt, likeCnt, writerInfo,sharingBoardTag.getContent());
            }
            else{
                simpleBoardResponse = new SimpleBoardResponse(sharingBoard, replyCnt, likeCnt, writerInfo);
            }

            simpleBoardResponseList.add(simpleBoardResponse);
        }
        return simpleBoardResponseList;
    }


}
