package udodog.goGetterServer.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.MessageRoom;
import udodog.goGetterServer.model.entity.MessageRoomJoin;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageRoomJoinRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRoomRepository messageRoomRepository;

    @Autowired
    private MessageRoomJoinRepository messageRoomJoinRepository;

    @Test
    public void save(){

        User user = User.builder().
                email("testEmail@gmail.com").
                phoneNumber("010-1234-5678").
                name("sender").
                nickName("user1Nickname").
                password("password").
                grade(UserGrade.USER).
                build();

        MessageRoom messageRoom = new MessageRoom();

        User saveUser = userRepository.save(user);
        MessageRoom saveMessageRoom = messageRoomRepository.save(messageRoom);

        MessageRoomJoin messageRoomJoin = MessageRoomJoin.builder()
                .messageRoom(saveMessageRoom)
                .user(saveUser)
                .build();

        MessageRoomJoin saveMessageRoomJoin = messageRoomJoinRepository.save(messageRoomJoin);

        Assertions.assertThat(saveMessageRoomJoin).isEqualTo(messageRoomJoin);

    }

}
