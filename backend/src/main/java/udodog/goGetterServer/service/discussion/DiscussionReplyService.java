package udodog.goGetterServer.service.discussion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyEditRequest;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyInsertRequest;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.DiscussionBoardReplyRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.disccussion.DiscussionBoardQueryRepository;
import udodog.goGetterServer.repository.querydsl.disccussion.DiscussionBoardReplyQueryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscussionReplyService {

    private final DiscussionBoardQueryRepository queryRepository;
    private final DiscussionBoardReplyRepository replyRepository;
    private final DiscussionBoardReplyQueryRepository replyQueryRepository;
    private final UserRepository userRepository;

    // 댓글 등록
    public DefaultRes createReply(DiscussionReplyInsertRequest requestDto, Long discussionId, Long userId) {

        Optional<DiscussionBoard> board = queryRepository.findById(discussionId);
        Optional<User> user = userRepository.findById(userId);

        if(requestDto == null) {
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        }

        if (board.get().getId().equals(discussionId) && user.get().getId().equals(userId)){
            replyRepository.save(requestDto.toEntity(board, user, requestDto));
        }

        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
    }

    // 댓글 조회
    public DefaultRes<Page<DiscussionReplyResponse>> getBoardReplyList(Long discussionId, Pageable pageable) {

        Page<DiscussionReplyResponse> replyResponsesPage = replyQueryRepository.findAllWithFetchJoin(discussionId, pageable);

        if (replyResponsesPage.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }else {
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", replyResponsesPage, new Pagination(replyResponsesPage));
        }
    }

    // 댓글 수정
    public DefaultRes updateReply(DiscussionReplyEditRequest requestDto, Long discussionId, Long replyId, Long userId) {

        Optional<DiscussionBoardReply> boardReply = replyQueryRepository.findByReplyId(replyId);

        if (boardReply.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }
        return boardReply.filter(board -> board.getDiscussionBoard().getId().equals(discussionId))
                .filter(board -> board.getId().equals(replyId))
                .filter(board -> board.getUser().getId().equals(userId))
                .map(board -> {

                    replyQueryRepository.updateBoard(requestDto, board.getId(), board.getUser().getId());

                    return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));

    }

    // 댓글 삭제
    public DefaultRes delete(Long discussionId, Long replyId, Long userId) {
        Optional<DiscussionBoardReply> deleteReply = replyQueryRepository.findByReplyId(replyId);

        if (deleteReply.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return deleteReply.filter(board -> board.getDiscussionBoard().getId().equals(discussionId))
                .filter(board -> board.getId().equals(replyId))
                .filter(board -> board.getUser().getId().equals(userId))
                .map(board -> {

                    replyQueryRepository.deleteById(board.getId(), board.getUser().getId());

                    return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "삭제실패"));
    }
}
