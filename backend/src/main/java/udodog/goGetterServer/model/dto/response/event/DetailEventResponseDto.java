package udodog.goGetterServer.model.dto.response.event;

import lombok.Getter;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.Event;

import java.time.LocalDate;

@Getter
public class DetailEventResponseDto {

    private Long id;

    private String title;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;

    private String imgUrl;

    private Long couponId;

    public DetailEventResponseDto(Event event, Coupon coupon) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.content = event.getContent();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.imgUrl = event.getImgUrl();
        this.couponId = coupon.getId();
    }

    public DetailEventResponseDto(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.content = event.getContent();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.imgUrl = event.getImgUrl();
    }
}
