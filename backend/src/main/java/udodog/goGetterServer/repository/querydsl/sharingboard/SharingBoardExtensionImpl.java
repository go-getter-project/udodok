package udodog.goGetterServer.repository.querydsl.sharingboard;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import udodog.goGetterServer.model.entity.QSharingBoard;
import udodog.goGetterServer.model.entity.SharingBoard;

import java.time.LocalDateTime;
import java.util.List;

public class SharingBoardExtensionImpl  extends QuerydslRepositorySupport implements SharingBoardExtension {

    public SharingBoardExtensionImpl() {
        super(SharingBoard.class);
    }

    @Override
    public List<SharingBoard> getWeeklyBest() {
        QSharingBoard sharingBoard = QSharingBoard.sharingBoard;

        LocalDateTime aWeekAgo = LocalDateTime.now().plusDays(-7);
        JPQLQuery<SharingBoard> query = from(sharingBoard).
                innerJoin(sharingBoard.user).
                fetchJoin().
                where(sharingBoard.createdAt.between(aWeekAgo, LocalDateTime.now())).
                orderBy(sharingBoard.likeCnt.desc())
                .limit(4);

        // 좋아요 순으로 정렬하기 위해 sharingBoard Entity에 likeCnt 필드 추가

        return query.fetch();
    }
}
