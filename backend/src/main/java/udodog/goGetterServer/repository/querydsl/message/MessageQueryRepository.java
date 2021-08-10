package udodog.goGetterServer.repository.querydsl.message;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.*;

import java.util.List;

import static udodog.goGetterServer.model.entity.QMessage.message;
import static udodog.goGetterServer.model.entity.QMessageRoomJoin.messageRoomJoin;
import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class MessageQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Message findMessage(MessageRoom messageRoom){

        return queryFactory.select(message)
                .from(message)
                .where(message.messageRoom.eq(messageRoom))
                .orderBy(message.sendAt.desc())
                .limit(1)
                .fetchOne();

    }

    public User findTheOtherUser(MessageRoom messageRoom, Long userId){
        return queryFactory.select(user)
                .from(messageRoomJoin)
                .where(messageRoomJoin.messageRoom.eq(messageRoom).and(messageRoomJoin.user.id.eq(userId).not()))
                .fetchOne();
    }

    public List<Message> findDetailMessage(Long messageRoomId){


        return queryFactory.select(message)
                .from(message)
                .where(message.messageRoom.id.eq(messageRoomId))
                .innerJoin(message.user, user)
                .fetchJoin()
                .orderBy(message.sendAt.asc())
                .fetch();

    }
}
