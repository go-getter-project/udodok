package udodog.goGetterServer.service.discussion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionEditRequest;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionInsertRequestDto;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionDetailResponse;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionResponseDto;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.DiscussionBoardReadhitRepository;
import udodog.goGetterServer.repository.DiscussionBoardRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.disccussion.DiscussionBoardQueryRepository;
import udodog.goGetterServer.repository.querydsl.disccussion.DiscussionBoardReadhitQueryRepository;
import udodog.goGetterServer.repository.querydsl.disccussion.DiscussionBoardReplyQueryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussionBoardRepository discussionBoardRepository;
    private final DiscussionBoardReadhitRepository readhitRepository;
    private final UserRepository userRepository;
    private final DiscussionBoardQueryRepository queryRepository;
    private final DiscussionBoardReplyQueryRepository replyQueryRepository;
    private final DiscussionBoardReadhitQueryRepository readhitQueryRepository;


    // 전체 목록 조회
    public DefaultRes<Page<DiscussionResponseDto>> getBoardList(Pageable pageable) {// 페이징 변수

        Page<DiscussionResponseDto> discussionBoardPage = queryRepository.findAllWithFetchJoin(pageable);

        if(discussionBoardPage.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }else{
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", discussionBoardPage, new Pagination(discussionBoardPage));
        }
    }

    // 게시글 상세 보기
    public DefaultRes<DiscussionDetailResponse> getDetailBoard(Long id, Long userId) {   // 게시판 id

        Optional<DiscussionBoard> discussionBoard = queryRepository.findById(id);
        Optional<DiscussionDetailResponse> detailBoard = queryRepository.findByDiscussionId(discussionBoard.get().getId());
        Optional<DiscussionBoardReadhit> readhit = readhitQueryRepository.findByDiscussionId(discussionBoard.get().getId());
        Optional<List<DiscussionBoardReply>> reply = replyQueryRepository.findByDiscussionId(discussionBoard.get().getId());

        LocalDate currentDate = LocalDate.now(); // 현재 날짜

        if(detailBoard.isEmpty() && readhit.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return discussionBoard.filter(board -> board.getId().equals(detailBoard.get().getId()))
                .filter(board -> board.getUser().getId().equals(userId))
                .map(board -> {
                    for(int i = 0; i < reply.get().size(); i++){
                        if(reply.get().get(i).getCreateAt().equals(currentDate)){
                            return DefaultRes.response(HttpStatus.OK.value(), "상세보기성공", new DiscussionDetailResponse(detailBoard));
                        }
                    }
                    return DefaultRes.response(HttpStatus.OK.value(), "상세보기성공", new DiscussionDetailResponse(detailBoard));
                }).orElse(discussionBoard.filter(board -> !(board.getUser().getId().equals(userId)))
                .map(board -> {
                    for(int i = 0; i < reply.get().size(); i++){
                        if(!(reply.get().get(i).getCreateAt().equals(currentDate))){

                            readhit.get().incrementCount();     // 조회수 카운트 증가
                            readhitQueryRepository.updateCount(id, readhit.get().getCount());    // 상세페이지를 보게 되면 조회 수 증가 값을 update

                            return DefaultRes.response(HttpStatus.OK.value(), "상세보기성공", new DiscussionDetailResponse(detailBoard));
                        }
                    }

                    readhit.get().incrementCount();     // 조회수 카운트 증가
                    readhitQueryRepository.updateCount(id, readhit.get().getCount());    // 상세페이지를 보게 되면 조회 수 증가 값을 update

                    return  DefaultRes.response(HttpStatus.OK.value(), "상세보기성공", new DiscussionDetailResponse(detailBoard));
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "상세보기실패")));


    }

    // 게시글 등록
    public DefaultRes insertBoard(DiscussionInsertRequestDto requestDto, Long userId) {  // 등록Dto

        Optional<User> user = userRepository.findById(userId);

        if(requestDto == null){
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        }

        Optional<DiscussionBoard> board = Optional.ofNullable(discussionBoardRepository.save(requestDto.toEntity(requestDto, user)));

        return board.map(saveboard -> {
            DiscussionBoardReadhit readhit = DiscussionBoardReadhit.builder()
                    .discussionBoard(saveboard)
                    .count(0)
                    .build();

            readhitRepository.save(readhit);

            return DefaultRes.response(HttpStatus.OK.value(), "등록성공");

            }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "등록실패"));
    }

    // 게시글 수정
    public DefaultRes updateBoard(DiscussionEditRequest update, Long id, Long userId) { // 업데이트Dto, 게시글 번호

        Optional<DiscussionBoard> optionalBoard =queryRepository.findById(id);

        if(optionalBoard.isEmpty()) {
            DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return optionalBoard.filter(board -> board.getId().equals(id))
                .filter(board -> board.getUser().getId().equals(userId))
                .map(board -> {

                    queryRepository.updateBoard(update, board.getId(), board.getUser().getId());

                    return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));
    }

    // 게시글 삭제
    public DefaultRes delete(Long id, Long userId) { // 게시글 번호, 유저번호
        Optional<DiscussionBoard> optionalBoard = queryRepository.findById(id);

        if (optionalBoard.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return optionalBoard.filter(board -> board.getId().equals(id))
                .filter(board -> board.getUser().getId().equals(userId))
                .map(board -> {
                    readhitQueryRepository.deleteByDiscussionId(board.getId());
                    replyQueryRepository.deleteByDiscussionId(board.getId());
                    queryRepository.deleteBoard(board.getId(), board.getUser().getId());

                    return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
                })
                .orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "삭제실패"));

    }

    // 제목 검색
    public DefaultRes<Page<DiscussionResponseDto>> searchTitle(String title, Pageable pageable) {

        Page<DiscussionResponseDto> boardPage = queryRepository.findByTitleContaining(title, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", boardPage, new Pagination(boardPage));
    }

    // 내용 검색
    public DefaultRes<Page<DiscussionResponseDto>> searchContent(String content, Pageable pageable) {

        Page<DiscussionResponseDto> boardPage = queryRepository.findByContentContaining(content, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", boardPage, new Pagination(boardPage));
    }

    // 제목 + 내용 검색
    public DefaultRes<Page<DiscussionResponseDto>> searchAll(String search, Pageable pageable) {
        Page<DiscussionResponseDto> boardPage = queryRepository.findByAllContaining(search, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", boardPage, new Pagination(boardPage));
    }

}