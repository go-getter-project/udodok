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
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyEditRequest;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QDiscussionBoardReply.discussionBoardReply;
import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class DiscussionBoardReplyQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    // 댓글 조회
    public Page<DiscussionReplyResponse> findAllWithFetchJoin(Long discussionId, Pageable pageable) {

        List<DiscussionReplyResponse> boardReplyList =
                queryFactory
                        .select(Projections.constructor(DiscussionReplyResponse.class,
                                discussionBoardReply.id,
                                user.id,
                                user.nickName,
                                discussionBoardReply.content,
                                discussionBoardReply.createAt))
                        .from(discussionBoardReply)
                        .innerJoin(discussionBoardReply.user, user)
                        .where(discussionBoardReply.discussionBoard.id.eq(discussionId))
                        .orderBy(discussionBoardReply.createAt.desc())
                        .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), boardReplyList.size());

        return new PageImpl<>(boardReplyList.subList(start, end), pageable, boardReplyList.size());
    }

    // 댓글 번호로 검색
    public Optional<DiscussionBoardReply> findByReplyId(Long replyId) {

        DiscussionBoardReply replyBoard =
                queryFactory
                        .selectFrom(discussionBoardReply)
                        .innerJoin(discussionBoardReply.user, user)
                        .fetchJoin()
                        .where(discussionBoardReply.id.eq(replyId))
                        .fetchOne();

        return Optional.ofNullable(replyBoard);
    }

    // 게시판 번호로 검색
    public Optional<List<DiscussionBoardReply>> findByDiscussionId(Long discussionId) {

        List<DiscussionBoardReply> replyBoard =
                queryFactory
                        .selectFrom(discussionBoardReply)
                        .innerJoin(discussionBoardReply.user, user)
                        .fetchJoin()
                        .where(discussionBoardReply.discussionBoard.id.eq(discussionId))
                        .fetch();

        return Optional.ofNullable(replyBoard);
    }

    // 댓글 수정
    @Transactional
    public void updateBoard(DiscussionReplyEditRequest requestDto, Long replyId, Long userId) { // 댓글 번호, 유저 번호가 같을 시 댓글 수정

        JPAUpdateClause updateClause = new JPAUpdateClause(em, discussionBoardReply);

        updateClause
                .where(discussionBoardReply.id.eq(replyId), discussionBoardReply.user.id.eq(userId))
                .set(discussionBoardReply.content, requestDto.getContent())
                .set(discussionBoardReply.createAt, requestDto.getCreateAt())
                .execute();
    }

    // 댓글 삭제
    @Transactional
    public void deleteById(Long replyId, Long userId) { // 댓글 번호, 유저 번호가 맞으면 댓글 삭제

        JPADeleteClause deleteClause = new JPADeleteClause(em, discussionBoardReply);

        deleteClause
                .where(discussionBoardReply.id.eq(replyId), discussionBoardReply.user.id.eq(userId))
                .execute();
    }

    // 게시판 번호로 댓글 삭제
    @Transactional
    public void deleteByDiscussionId(Long discussionId) {   // 게시판 삭제 될 시 댓글도 같이 삭제
        JPADeleteClause deleteClause = new JPADeleteClause(em, discussionBoardReply);

        deleteClause
                .where(discussionBoardReply.discussionBoard.id.eq(discussionId))
                .execute();
    }
}
