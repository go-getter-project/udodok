package udodog.goGetterServer.repository.querydsl.disccussion;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;

import javax.persistence.EntityManager;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QDiscussionBoardReadhit.discussionBoardReadhit;

@RequiredArgsConstructor
@Repository
public class DiscussionBoardReadhitQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    // 게시판 번호로 조회수 찾기
    public Optional<DiscussionBoardReadhit> findByDiscussionId(Long discussionId){

        DiscussionBoardReadhit readhit =
                queryFactory
                        .selectFrom(discussionBoardReadhit)
                        .where(discussionBoardReadhit.discussionBoard.id.eq(discussionId))
                        .fetchOne();

        return Optional.ofNullable(readhit);
    }

    @Transactional
    public void updateCount(Long discussionId, Integer count){  // 게시판 번호에 맞게 조회수 증가
        JPAUpdateClause updateClause = new JPAUpdateClause(em, discussionBoardReadhit);

        updateClause
                .where(discussionBoardReadhit.discussionBoard.id.eq(discussionId))
                .set(discussionBoardReadhit.count, count)
                .execute();
    }

    @Transactional
    public void deleteByDiscussionId(Long discussionId) { // 게시판 삭제 될 시 조회수 삭제

        JPADeleteClause deleteClause = new JPADeleteClause(em, discussionBoardReadhit);

        deleteClause
                .where(discussionBoardReadhit.discussionBoard.id.eq(discussionId))
                .execute();
    }
}
