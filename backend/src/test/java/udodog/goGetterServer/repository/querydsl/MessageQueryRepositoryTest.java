package udodog.goGetterServer.repository.querydsl;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.config.TestConfig;
import udodog.goGetterServer.model.entity.MessageRoomJoin;
import udodog.goGetterServer.repository.MessageRoomJoinRepository;
import udodog.goGetterServer.repository.querydsl.message.MessageQueryRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
@Slf4j
public class MessageQueryRepositoryTest {

    @Autowired
    private MessageQueryRepository messageQueryRepository;

    @Autowired
    private MessageRoomJoinRepository messageRoomJoinRepository;

    //N +1 이슈 확인 테스트.
    @Test
    public void 사용자_최근_메시지_조회(){

        Long userId = 890L;
        List<MessageRoomJoin> messageRoomJoinList = messageRoomJoinRepository.findAllByUserId(userId);

        messageQueryRepository.findMessage(messageRoomJoinList.get(0).getMessageRoom());

    }

    //N +1 이슈 확인 테스트.
    @Test
    public void 상대방_정보_조회(){

        Long userId = 890L;
        List<MessageRoomJoin> messageRoomJoinList = messageRoomJoinRepository.findAllByUserId(userId);
        messageQueryRepository.findTheOtherUser(messageRoomJoinList.get(0).getMessageRoom(), userId);

    }
}
