package udodog.goGetterServer.service.event;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.event.EventCreateRequestDto;
import udodog.goGetterServer.model.dto.request.event.EventUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.event.DetailEventResponseDto;
import udodog.goGetterServer.model.dto.response.event.EventsResponseDto;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.Event;
import udodog.goGetterServer.repository.CouponRepository;
import udodog.goGetterServer.repository.EventRepository;
import udodog.goGetterServer.repository.querydsl.EventQueryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@Slf4j
public class EventServiceTest {

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepository eventRepository;

    @Mock
    EventQueryRepository eventQueryRepository;

    @Mock
    CouponRepository couponRepository;

    @Test
    public void 이벤트_생성(){

        //given
        String title = "신규 회원 등록 이벤트";
        String content = "20% 할인 쿠폰 지급";
        LocalDate startData = LocalDate.of(2021,7,1);
        LocalDate endDate = LocalDate.of(2021,7,15);
        String imgUrl = null;
        Coupon coupon = new Coupon();
        Long couponId = 1L;

        EventCreateRequestDto eventCreateRequestDto = new EventCreateRequestDto(title, content, startData, endDate, imgUrl, couponId);

        Event mockEvent = eventCreateRequestDto.toEntity(coupon);
        given(couponRepository.findById(couponId)).willReturn(Optional.of(coupon));
        //when
        given(eventRepository.save(any())).willReturn(mockEvent);
        DefaultRes defaultRes = eventService.eventCreate(eventCreateRequestDto);

        //then
        assertThat(defaultRes.getMessage()).isEqualTo("등록성공");

    }

    @Test
    public void 진행중인_이벤트_전체조회(){

        //given

        List<EventsResponseDto> eventList = new ArrayList<>();

        Long id = 1L;
        String title = "신규 회원 등록 이벤트";
        String content = "20% 할인 쿠폰 지급";
        LocalDate startData = LocalDate.of(2021,7,1);
        LocalDate endDate = LocalDate.of(2021,7,15);
        String imgUrl = null;

        Long id2 = 2L;
        String title2 = "신규 회원 등록 이벤트2";
        String content2 = "20% 할인 쿠폰 지급2";
        LocalDate startData2 = LocalDate.of(2021,7,15);
        LocalDate endDate2 = LocalDate.of(2021,7,20);
        String imgUrl2 = null;

        EventsResponseDto event1 = new EventsResponseDto(id, title, startData, endDate);
        EventsResponseDto event2 = new EventsResponseDto(id2, title, startData, endDate);

        eventList.add(event1);
        eventList.add(event2);

        //when
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("startDate").descending());
        Page<EventsResponseDto> eventPage = new PageImpl<>(eventList, pageRequest, 2);
        given(eventQueryRepository.progressEventFindAll(pageRequest)).willReturn(eventPage);

        DefaultRes<Page<EventsResponseDto>> result = eventService.progressEventFindAll(pageRequest);

        //then
        assertThat(result.getMessage()).isEqualTo("조회성공");
        assertThat(result.getData().getTotalElements()).isEqualTo(2);
    }

    @Test
    public void 진행중인_이벤트_상세조회(){

        //given

        Long id = 1L;
        String title = "신규 회원 등록 이벤트";
        String content = "20% 할인 쿠폰 지급";
        LocalDate startData = LocalDate.of(2021,7,1);
        LocalDate endDate = LocalDate.of(2021,7,15);
        String imgUrl = null;
        Coupon coupon = new Coupon();

        Event event = new Event(id, title, content, startData, endDate, imgUrl, coupon);
        given(eventRepository.findById(id)).willReturn(Optional.of(event));

        //when
        DefaultRes<DetailEventResponseDto> result = eventService.eventDetailFind(id);

        //then
        assertThat(result.getMessage()).isEqualTo("조회성공");
        assertThat(result.getData().getId()).isEqualTo(id);
        assertThat(result.getData().getTitle()).isEqualTo(title);
        assertThat(result.getData().getCouponId()).isEqualTo(coupon.getId());
    }

    @Test
    public void 이벤트_업데이트(){
        //given
        Long id = 1L;
        String title = "신규 회원 등록 이벤트";
        String content = "20% 할인 쿠폰 지급";
        LocalDate startData = LocalDate.of(2021,7,10);
        LocalDate endDate = LocalDate.of(2021,7,15);
        String imgUrl = "test.jpg";
        Coupon coupon = new Coupon();
        Event event = new Event(id, title, content, startData, endDate, imgUrl, coupon);

        given(eventRepository.save(any())).willReturn(event);

        String updateTitle = "신규 회원 파격 이벤트";
        String updateContent = "5만원 쿠폰 지급";
        EventUpdateRequestDto request = new EventUpdateRequestDto(updateTitle, updateContent, imgUrl, coupon.getId());

        given(eventRepository.findById(1L)).willReturn(Optional.of(event));
        given(couponRepository.findById(coupon.getId())).willReturn(Optional.of(coupon));

        //when
        DefaultRes defaultRes1 = eventService.eventUpdate(1L, request);

        DefaultRes defaultRes2 = eventService.eventUpdate(2L, request);

        //then
        assertThat(defaultRes1.getStatusCode()).isEqualTo(200); // 업데이트 성공시 200
        assertThat(defaultRes1.getMessage()).isEqualTo("업데이트성공");
        assertThat(defaultRes2.getStatusCode()).isEqualTo(200); // 업데이트 실패시 200 데이터없음



    }

    @Test
    public void 이벤트_삭제(){
        //given
        Long id = 1L;
        String title = "신규 회원 등록 이벤트";
        String content = "20% 할인 쿠폰 지급";
        LocalDate startData = LocalDate.of(2021,7,10);
        LocalDate endDate = LocalDate.of(2021,7,15);
        String imgUrl = "test.jpg";
        Coupon coupon = new Coupon();
        Event event = new Event(id, title, content, startData, endDate, imgUrl, coupon);

        //when
        given(eventRepository.findById(id)).willReturn(Optional.of(event));
        DefaultRes result = eventService.eventDelete(1L);

        //then
        assertThat(result.getMessage()).isEqualTo("삭제성공");
    }
}
