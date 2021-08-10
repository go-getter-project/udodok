package udodog.goGetterServer.service.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.mypage.MyPageRequestDto;
import udodog.goGetterServer.model.dto.response.mypage.MyPageResponseDto;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.querydsl.MyPageQueryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageQueryRepository myPageQueryRepository;

    public DefaultRes<MyPageResponseDto> getUserInfo(Long userId) {

        Optional<MyPageResponseDto> userInfo = myPageQueryRepository.findById(userId);
        if (userInfo.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", new MyPageResponseDto(userInfo));
    }

    public DefaultRes updateUserInfo(Long userId, MyPageRequestDto requestDto) {

        Optional<User> userInfo = myPageQueryRepository.findByUserId(userId);

        if (userInfo.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return userInfo.filter(detailUser -> detailUser.getId().equals(userId))
                .map(detailUser -> {
                    myPageQueryRepository.modifyUserInfo(requestDto, detailUser.getId());

                    return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));

    }
}
