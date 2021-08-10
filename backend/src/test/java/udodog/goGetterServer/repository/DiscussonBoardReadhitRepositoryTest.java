package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DiscussonBoardReadhitRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscussionBoardRepository discussonBoardRepository;

    @Autowired
    private DiscussionBoardReplyRepository discussionBoardReplyRepository;

    @Autowired
    private DiscussionBoardReadhitRepository discussionReplyCountRepository;

    @Test
    void saveDiscussiontReadhit(){

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

        DiscussionBoard saveDiscussionBoard = discussonBoardRepository.save(discussionBoard);

        DiscussionBoardReply discussionBoardReply = DiscussionBoardReply.builder()
                .discussionBoard(saveDiscussionBoard)
                .user(saveUser)
                .content("토론게시판 댓글 테스트 내용 입니다.")
                .build();

        DiscussionBoardReply saveDiscussionBoardReply = discussionBoardReplyRepository.save(discussionBoardReply);

        DiscussionBoardReadhit discussionBoardCount = DiscussionBoardReadhit.builder()
                .discussionBoard(saveDiscussionBoard)
                .count(1)
                .build();

        //when
        DiscussionBoardReadhit savediscussionBoardCount = discussionReplyCountRepository.save(discussionBoardCount);

        //then
        assertThat(discussionBoardCount).isEqualTo(savediscussionBoardCount);
    }
}