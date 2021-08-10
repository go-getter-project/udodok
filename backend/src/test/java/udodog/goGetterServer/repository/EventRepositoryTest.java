package udodog.goGetterServer.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.Event;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void 이벤트_저장(){

        //given
        Event event = Event.builder()
                .title("서비스 오픈 기념")
                .content("안녕하세요")
                .startDate(LocalDate.of(2021,06,16))
                .endDate(LocalDate.of(2021,07,16))
                .build();

        //when
        Event saveEvent = eventRepository.save(event);

        //then
        assertThat(event).isEqualTo(saveEvent);

    }

    @Test
    void 이벤트_조회_N1테스트(){
        Optional<Event> optionalEvent = eventRepository.findById(163L);
        log.info("event = {}" , optionalEvent.get().getCoupon());

    }
}
