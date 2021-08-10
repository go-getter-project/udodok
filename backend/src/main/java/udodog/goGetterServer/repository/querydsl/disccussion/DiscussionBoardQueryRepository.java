package udodog.goGetterServer.repository.querydsl.disccussion;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionEditRequest;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionDetailResponse;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionResponseDto;
import udodog.goGetterServer.model.entity.DiscussionBoard;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QDiscussionBoard.discussionBoard;
import static udodog.goGetterServer.model.entity.QDiscussionBoardReadhit.discussionBoardReadhit;
import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class DiscussionBoardQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    // 게시판 전체 조회
    public Page<DiscussionResponseDto> findAllWithFetchJoin(Pageable pageable) {

        List<DiscussionResponseDto> boardList =
                queryFactory
                        .select(Projections.constructor(DiscussionResponseDto.class,
                                discussionBoard.id.as("discussionId"),
                                user.nickName,
                                discussionBoard.title,
                                discussionBoard.createAt,
                                discussionBoardReadhit.count))
                        .from(discussionBoard)
                        .innerJoin(discussionBoard.user, user)
                        .join(discussionBoardReadhit).on(discussionBoardReadhit.discussionBoard.id.eq(discussionBoard.id))
                        .orderBy(discussionBoard.createAt.desc())
                        .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), boardList.size());

        return new PageImpl<>(boardList.subList(start, end), pageable, boardList.size());

    }

    // 게시판 상세 조회
    public Optional<DiscussionDetailResponse> findByDiscussionId(Long id){

        DiscussionDetailResponse board =
                queryFactory
                        .select(Projections.constructor(DiscussionDetailResponse.class,
                                discussionBoard.id,
                                user.id,
                                user.nickName,
                                discussionBoard.title,
                                discussionBoard.content,
                                discussionBoard.createAt,
                                discussionBoardReadhit.count))
                        .from(discussionBoard)
                        .innerJoin(discussionBoard.user, user)
                        .join(discussionBoardReadhit).on(discussionBoardReadhit.discussionBoard.id.eq(discussionBoard.id))
                        .where(discussionBoard.id.eq(id))
                        .fetchOne();

        return Optional.ofNullable(board);

    }

    // 수정, 삭제시 게시글 찾기
    public Optional<DiscussionBoard> findById(Long id){

        DiscussionBoard board =
                queryFactory
                        .selectFrom(discussionBoard)
                        .innerJoin(discussionBoard.user, user)
                        .where(discussionBoard.id.eq(id))
                        .fetchOne();

        return Optional.ofNullable(board);

    }

    @Transactional
    // 게시판 수정
    public void updateBoard(DiscussionEditRequest update, Long id, Long userId) {

        JPAUpdateClause updateClause = new JPAUpdateClause(em, discussionBoard);

        updateClause
                .where(discussionBoard.id.eq(id), discussionBoard.user.id.eq(userId))
                .set(discussionBoard.title, update.getTitle())
                .set(discussionBoard.content, update.getContent())
                .set(discussionBoard.createAt, update.getCreateAt())
                .execute();
    }

    @Transactional
    // 게시판 삭제
    public void deleteBoard(Long id, Long userId) {

        JPADeleteClause deleteClause = new JPADeleteClause(em, discussionBoard);

        deleteClause
                .where(discussionBoard.id.eq(id), discussionBoard.user.id.eq(userId))
                .execute();
    }

    // 제목으로 검색
    public Page<DiscussionResponseDto> findByTitleContaining(String title, Pageable pageable) {

        List<DiscussionResponseDto> boardList =
                queryFactory
                        .select(Projections.constructor(DiscussionResponseDto.class,
                                discussionBoard.id,
                                user.nickName,
                                discussionBoard.title,
                                discussionBoard.createAt,
                                discussionBoardReadhit.count))
                        .from(discussionBoard)
                        .innerJoin(discussionBoard.user, user)
                        .join(discussionBoardReadhit).on(discussionBoardReadhit.discussionBoard.id.eq(discussionBoard.id))
                        .where(discussionBoard.title.contains(title))
                        .orderBy(discussionBoard.id.desc())
                        .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), boardList.size());

        return new PageImpl<>(boardList.subList(start, end), pageable, boardList.size());

    }

    // 내용으로 검색
    public Page<DiscussionResponseDto> findByContentContaining(String content, Pageable pageable) {

        List<DiscussionResponseDto> boardList =
                queryFactory
                        .select(Projections.constructor(DiscussionResponseDto.class,
                                discussionBoard.id,
                                user.nickName,
                                discussionBoard.title,
                                discussionBoard.createAt,
                                discussionBoardReadhit.count))
                        .from(discussionBoard)
                        .innerJoin(discussionBoard.user, user)
                        .join(discussionBoardReadhit).on(discussionBoardReadhit.discussionBoard.id.eq(discussionBoard.id))
                        .where(discussionBoard.content.contains(content))
                        .orderBy(discussionBoard.id.desc())
                        .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), boardList.size());

        return new PageImpl<>(boardList.subList(start, end), pageable, boardList.size());

    }

    // 제목 + 내용으로 검색
    public Page<DiscussionResponseDto> findByAllContaining(String search, Pageable pageable) {

        List<DiscussionResponseDto> boardList =
                queryFactory
                        .select(Projections.constructor(DiscussionResponseDto.class,
                                discussionBoard.id,
                                user.nickName,
                                discussionBoard.title,
                                discussionBoard.createAt,
                                discussionBoardReadhit.count))
                        .from(discussionBoard)
                        .innerJoin(discussionBoard.user, user)
                        .join(discussionBoardReadhit).on(discussionBoardReadhit.discussionBoard.id.eq(discussionBoard.id))
                        .where(discussionBoard.title.contains(search).or(discussionBoard.content.contains(search)))
                        .orderBy(discussionBoard.id.desc())
                        .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), boardList.size());

        return new PageImpl<>(boardList.subList(start, end), pageable, boardList.size());
    }
}
