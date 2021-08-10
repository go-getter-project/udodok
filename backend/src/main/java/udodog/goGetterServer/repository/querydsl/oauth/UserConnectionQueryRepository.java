package udodog.goGetterServer.repository.querydsl.oauth;

import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.UserConnection;

import javax.persistence.EntityManager;

import static udodog.goGetterServer.model.entity.QUserConnection.userConnection;

@RequiredArgsConstructor
@Repository
public class UserConnectionQueryRepository {

    private final EntityManager em;

    @Transactional
    public void updatePassword(String socialEmail, String access_token) {

        JPAUpdateClause updateClause = new JPAUpdateClause(em, userConnection);

        updateClause
                .where(userConnection.email.eq(socialEmail))
                .set(userConnection.accessToken, access_token)
                .execute();
    }
}
