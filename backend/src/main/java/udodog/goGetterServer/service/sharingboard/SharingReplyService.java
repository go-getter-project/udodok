package udodog.goGetterServer.service.sharingboard;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateSharingReplyRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.CreateSharingReplyRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.DeleteSharingReplyRequest;
import udodog.goGetterServer.model.dto.response.sharingboard.SharingReplyResponse;
import udodog.goGetterServer.model.dto.response.sharingboard.WriterInfo;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardReply;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.SharingBoardReplyRepository;
import udodog.goGetterServer.repository.SharingBoardRepository;
import udodog.goGetterServer.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SharingReplyService {

    private final SharingBoardReplyRepository sharingBoardReplyRepository;
    private final UserRepository userRepository;
    private final SharingBoardRepository sharingBoardRepository;

    public DefaultRes<List<SharingReplyResponse>> getReplyList(Long boardId, Pageable pageable) {

        Page<SharingBoardReply> sharingBoardReplies = sharingBoardReplyRepository.findAll(boardId, pageable);

        if (sharingBoardReplies.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"데이터 없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"조회 성공",getSharingReplyResponse(sharingBoardReplies),new Pagination(sharingBoardReplies));

    }

    private List<SharingReplyResponse> getSharingReplyResponse(Page<SharingBoardReply> sharingBoardReplies) {
        List<SharingReplyResponse> sharingReplyResponseList = new LinkedList<>();

        for(SharingBoardReply sharingBoardReply : sharingBoardReplies){
            User user = sharingBoardReply.getUser();
            WriterInfo writerInfo = WriterInfo.builder().nickName(user.getNickName()).profileUrl(user.getProfileUrl()).writerId(user.getId()).build();

            SharingReplyResponse sharingReplyResponse = new SharingReplyResponse(sharingBoardReply, writerInfo);
            sharingReplyResponseList.add(sharingReplyResponse);
        }
        return sharingReplyResponseList;
    }

    public DefaultRes createReply(Long boardId, CreateSharingReplyRequest request) {
        User user = userRepository.findById(request.getUserId()).get();
        // 인터셉터에서 확인을 해줬기 때문에 user는 null이 아님.

        Optional<SharingBoard> board = sharingBoardRepository.findById(boardId);
        if (board.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"댓글 등록 실패");
        }

        SharingBoardReply sharingBoardReply = new SharingBoardReply(request, user, board.get());
        SharingBoardReply saveSharingBoardReply = sharingBoardReplyRepository.save(sharingBoardReply);

        if(board.get().getId().equals(saveSharingBoardReply.getSharingBoard().getId())){
            return DefaultRes.response(HttpStatus.OK.value(),"댓글 등록 성공");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"댓글 등록 실패");

    }

    public DefaultRes updateReply(Long boardId, UpdateSharingReplyRequest request) {
        Optional<SharingBoardReply> replyById = sharingBoardReplyRepository.findById(request.getReplyId());
        Optional<SharingBoard> boardById = sharingBoardRepository.findById(boardId);

        if (replyById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"댓글이 존재하지 않음");
        }

        if (boardById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글이 존재하지 않음");
        }

        // UPDATE
        SharingBoardReply updateBoardReply = replyById.get().updateBoard(request);
        SharingBoardReply saveBoardReply = sharingBoardReplyRepository.save(updateBoardReply);
        //

        if(saveBoardReply.getId().equals(request.getReplyId())){
            return DefaultRes.response(HttpStatus.OK.value(),"댓글 수정 성공");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"댓글 수정 실패");
    }


    public DefaultRes deleteReply(Long boardId, DeleteSharingReplyRequest request) {
        Optional<SharingBoardReply> replyById = sharingBoardReplyRepository.findById(request.getReplyId());
        Optional<SharingBoard> boardById = sharingBoardRepository.findById(boardId);

        if (replyById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"댓글이 존재하지 않음");
        }

        if (boardById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글이 존재하지 않음");
        }

        //본인이 작성한 글이 아니면 삭제 x
        if (!request.getUserId().equals(replyById.get().getUser().getId())){
            return DefaultRes.response(HttpStatus.OK.value(),"댓글 삭제 실패");
        }

        //삭제
        sharingBoardReplyRepository.delete(replyById.get());

        return DefaultRes.response(HttpStatus.OK.value(),"글 삭제 성공");
    }
}
