package udodog.goGetterServer.service.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.coupon.CouponCreateRequestDto;
import udodog.goGetterServer.model.dto.response.coupon.selectbox.CouponSelectBoxResponseDto;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.CouponUseHistory;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.CouponRepository;
import udodog.goGetterServer.repository.CouponUseHistoryRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.CouponQueryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final UserRepository userRepository;

    private final CouponRepository couponRepository;

    private final CouponUseHistoryRepository couponUseHistoryRepository;

    private final CouponQueryRepository couponQueryRepository;

    public DefaultRes couponCreate(CouponCreateRequestDto request){

        couponRepository.save(request.toEntity());

        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");

    }

    @Transactional
    public DefaultRes couponDownload(Long userId, Long couponId){

        Boolean overlapcheck = couponQueryRepository.overlapCoupon(userId, couponId);

        if(overlapcheck){
            Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
            Optional<User> optionalUser = userRepository.findById(userId);

            if(optionalCoupon.isPresent() && optionalUser.isPresent()){

                if(optionalCoupon.get().getQuantity() != 0){

                    LocalDate endDate = LocalDate.now().plusDays(optionalCoupon.get().getValidDate());
                    String serialNumber = "";

                    for (int i = 0; i < 20; i++){
                        serialNumber += Integer.toString(new Random().nextInt(10));
                    }

                    couponUseHistoryRepository.save(new CouponUseHistory(optionalUser.get(), optionalCoupon.get(), serialNumber, endDate));
                    optionalCoupon.get().decreaseQuantity();
                    return DefaultRes.response(HttpStatus.OK.value(), "다운로드성공");

                }else{
                    return DefaultRes.response(HttpStatus.OK.value(), "실패(수량0)");
                }

            }else {
                return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
            }

        }else{
            return DefaultRes.response(HttpStatus.OK.value(), "실패(중복)");
        }
    }

    public DefaultRes<List<CouponSelectBoxResponseDto>> couponSelectBox(){

        List<Coupon> couponList = couponRepository.findAll();

        if(couponList.size() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        List<CouponSelectBoxResponseDto> couponSelectBoxResponseDtoList = couponList.stream()
                .map(coupon -> new CouponSelectBoxResponseDto(coupon.getId(), coupon.getName()))
                .collect(Collectors.toList());

        return DefaultRes.response(HttpStatus.OK.value(), "조회성공",couponSelectBoxResponseDtoList);
    }
}
