package udodog.goGetterServer.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.event.EventCreateRequestDto;
import udodog.goGetterServer.model.dto.request.event.EventUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.event.DetailEventResponseDto;
import udodog.goGetterServer.model.dto.response.event.EventsResponseDto;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.Event;
import udodog.goGetterServer.repository.CouponRepository;
import udodog.goGetterServer.repository.EventRepository;
import udodog.goGetterServer.repository.querydsl.EventQueryRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository eventRepository;

    private final EventQueryRepository eventQueryRepository;

    private final CouponRepository couponRepository;

    public DefaultRes eventCreate(EventCreateRequestDto request){

        if (request.getCouponId() != null){

            Optional<Coupon> optionalCoupon = couponRepository.findById(request.getCouponId());
            return optionalCoupon.map(coupon -> {
                eventRepository.save(request.toEntity(coupon));
                return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
            }).orElseGet(()->DefaultRes.response(HttpStatus.OK.value(),"쿠폰없음"));

        }else{

            eventRepository.save(request.toEntity(null));
            return DefaultRes.response(HttpStatus.OK.value(), "등록성공");

        }

    }

    public DefaultRes<Page<EventsResponseDto>> progressEventFindAll(Pageable pageable){

        Page<EventsResponseDto> result = eventQueryRepository.progressEventFindAll(pageable);

        if(result.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }
        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", result, new Pagination(result));
    }

    public DefaultRes<Page<EventsResponseDto>> endEventFindAll(Pageable pageable){

        Page<EventsResponseDto> result = eventQueryRepository.endEventFindAll(pageable);

        if(result.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }
        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", result, new Pagination(result));
    }

    public DefaultRes<DetailEventResponseDto> eventDetailFind(Long eventId){

        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        return optionalEvent
                .map(event -> {
                    if(event.getCoupon() != null){
                        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", new DetailEventResponseDto(event, event.getCoupon()));
                    }else{
                        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", new DetailEventResponseDto(event));
                    }
                })
                .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "데이터없음"));

    }

    @Transactional
    public DefaultRes eventUpdate(Long eventId, EventUpdateRequestDto request){

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Optional<Coupon> optionalCoupon = couponRepository.findById(request.getCouponId());

        if (optionalCoupon.isPresent()){

            return optionalEvent.map(event ->{
                event.update(request, optionalCoupon.get());
                return DefaultRes.response(HttpStatus.OK.value(), "업데이트성공");
            }).orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "데이터없음"));
        }else{
            return DefaultRes.response(HttpStatus.OK.value(), "쿠폰없음");
        }

    }

    @Transactional
    public DefaultRes eventDelete(Long eventId){

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.map(event -> {
            eventRepository.delete(event);
            return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
        }).orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "데이터없음"));

    }

}
