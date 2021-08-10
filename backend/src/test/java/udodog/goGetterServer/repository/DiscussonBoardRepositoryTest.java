package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DiscussonBoardRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscussionBoardRepository discussonBoardRepository;

    @Test
    void saveDicussion(){

        //given
        User user = User.builder()
                .email("hwoo00oo96@gmail.com")
                .password("1234")
                .name("변현우")
                .nickName("woo00oo")
                .phoneNumber("010-9245-7396")
                .grade(UserGrade.USER)
                .build();

        User saveUser = userRepository.save(user);


        DiscussionBoard discussionBoard = DiscussionBoard.builder()
                .user(saveUser)
                .title("토론 게시판 테스트입니다.")
                .content("토론 게시판 테스트 내용 입니다.")
                .build();

        //when
        DiscussionBoard saveDiscussion = discussonBoardRepository.save(discussionBoard);

        //then
        assertThat(discussionBoard).isEqualTo(saveDiscussion);
    }
}