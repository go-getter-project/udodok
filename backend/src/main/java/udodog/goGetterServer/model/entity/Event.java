package udodog.goGetterServer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.dto.request.event.EventUpdateRequestDto;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;

    private String imgUrl;

    @ManyToOne(targetEntity = Coupon.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Builder
    public Event(String title, String content, LocalDate startDate, LocalDate endDate, String imgUrl, Coupon coupon) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgUrl = imgUrl;
        this.coupon = coupon;
    }

    public void update(EventUpdateRequestDto request, Coupon coupon){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.imgUrl = request.getImgUrl();
        this.coupon = coupon;
    }
}
