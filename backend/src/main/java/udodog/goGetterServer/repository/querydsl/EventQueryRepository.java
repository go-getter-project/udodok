package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.dto.response.event.EventsResponseDto;

import java.time.LocalDate;
import java.util.List;

import static udodog.goGetterServer.model.entity.QEvent.event;

@RequiredArgsConstructor
@Repository
public class EventQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<EventsResponseDto> progressEventFindAll(Pageable pageable){

        List<EventsResponseDto> eventList = queryFactory
                .select(Projections.constructor(EventsResponseDto.class,
                        event.id.as("eventId"),
                        event.title,
                        event.startDate,
                        event.endDate))
                .from(event)
                .where(event.endDate.after(LocalDate.now().minusDays(1)))
                .orderBy(event.startDate.desc())
                .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), eventList.size());

        return new PageImpl<>(eventList.subList(start, end), pageable, eventList.size());

    }

    public Page<EventsResponseDto> endEventFindAll(Pageable pageable){

        List<EventsResponseDto> eventList = queryFactory
                .select(Projections.constructor(EventsResponseDto.class,
                        event.id.as("eventId"),
                        event.title,
                        event.startDate,
                        event.endDate))
                .from(event)
                .where(event.endDate.before(LocalDate.now().minusDays(1)))
                .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), eventList.size());

        return new PageImpl<>(eventList.subList(start, end), pageable, eventList.size());

    }


}
