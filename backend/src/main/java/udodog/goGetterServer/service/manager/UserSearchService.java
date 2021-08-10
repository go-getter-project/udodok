package udodog.goGetterServer.service.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.manager.MemberJoinCountRequestDto;
import udodog.goGetterServer.model.dto.request.manager.MemberUpdateGradeRequestDto;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.model.dto.response.manager.visuallization.MemberJoinVisuallizationResponseDto;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.repository.querydsl.UserQueryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSearchService {

    private final UserQueryRepository userQueryRepository;

    // 전체 조회
    public DefaultRes<Page<UserSearchResponseDto>> searchUserList ( Pageable pageable) { // 페이징 처리

        Page<UserSearchResponseDto> memberPage = userQueryRepository.findAll(pageable);

        if (memberPage.getTotalElements() == 0) { // 가입된 회원이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");

        } else {                                  // 가입 회원이 있다면?
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", memberPage, new Pagination(memberPage));
        } // if-else 문 끝
    } // searchUserList() 끝

    // 회원 상세 조회
    public DefaultRes<UserSearchResponseDto> getMemberDetail (Long userId) {
        Optional<UserSearchResponseDto> memberById = userQueryRepository.findById(userId);

        if (memberById.isEmpty()) { // 상세 조회를 원하는 Member 정보가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터 없음");
        } else {
            return memberById.map(userSearch -> DefaultRes.response(HttpStatus.OK.value(), "조회성공", userSearch))
                    .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "조회실패"));
        } // if-else문 끝

    } // getMemberDetail()끝

    // 회원 등급 변경
    public DefaultRes updateMemberGrade(Long userId, MemberUpdateGradeRequestDto updateDto) {
        Optional<User> byUserGrade = userQueryRepository.findByUserId(userId);

        if (byUserGrade.isEmpty()) {  // 대상 회원이 없다면?
            DefaultRes.response(HttpStatus.OK.value(), "내용없음");
        } // if 문 끝

        return byUserGrade.filter(user -> user.getId().equals(userId))
                          .map(user -> {

                              userQueryRepository.updateMemberGrade(updateDto, user.getId());

                              return DefaultRes.response(HttpStatus.OK.value(), "수정성공"); })
                            .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));
    } // updateMemberGrade()끝

    // Black 회원 강제 탈퇴 Method
    public DefaultRes blackMemberWithdrawal(Long userId, UserGrade userGrade) {

        Optional<User> userOptional = userQueryRepository.findByUserGrade(userGrade, userId);

        if (userOptional.isEmpty()) {   // 해당 Black 회원이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        } // if문 끝

        if (userOptional.get().getGrade().equals(UserGrade.USER)) {     // BLACK 등급만 삭제 가능하기 위한 유효성 검사
            return DefaultRes.response(HttpStatus.OK.value(), "Black등급만 삭제가능");
        } else if (userOptional.get().getGrade().equals(UserGrade.ADMIN)) {
            return DefaultRes.response(HttpStatus.OK.value(), "Black등급만 삭제가능");
        } // 유효성 검사 끝

        return userOptional.filter(detailMember -> detailMember.getId().equals(userId))
                .map(detailMember -> {

                        userQueryRepository.blackMemberWithdrawal(userId, userGrade);

                    return DefaultRes.response(HttpStatus.OK.value(), "강제탈퇴 성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "강제탈퇴 실패"));
    } // blackMemberWithdrawal() 끝

    // 월별 회원 가입 수 Count Method
    public DefaultRes<List<MemberJoinVisuallizationResponseDto>> memberJoinCount(String year) {
        List<MemberJoinVisuallizationResponseDto> searchUserList = userQueryRepository.findByCreatedAt(year);

        if (searchUserList.isEmpty()) { // 해당 시점에 회원 가입이 없다면?
            DefaultRes.response(HttpStatus.OK.value(), "가입수없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "가입수 찾음", searchUserList);

    } // memberJoinCount() 끝

    // ################################# 검색 기능 ####################################

    // 회원 이름으로 검색
    public DefaultRes<Page<UserSearchResponseDto>> memberNameSearch (String name, Pageable pageable) {
        Page<UserSearchResponseDto> memberByName = userQueryRepository.findByName(name, pageable);

        if (memberByName.isEmpty()) { // 회원 이름 검색 시 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색 결과 없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색완료", memberByName);
    } // memberNameSearch()끝

    // 회원 Email로 검색
    public DefaultRes<Page<UserSearchResponseDto>> memberEmailSearch(String email, Pageable pageable) {
        Page<UserSearchResponseDto> memberByEmail = userQueryRepository.findByEmail(email, pageable);

        if (memberByEmail.isEmpty()) { // 회원 Email 검색 시 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색 결과 없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색완료", memberByEmail);
    } // memberEmailSearch() 끝

    // 회원 별명 검색
    public DefaultRes<Page<UserSearchResponseDto>> memberNickNameSearch(String nickName, Pageable pageable) {
        Page<UserSearchResponseDto> memberByNickName = userQueryRepository.findByNickName(nickName, pageable);

        if (memberByNickName.isEmpty()) { // 회원 Email 검색 시 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색 결과 없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색완료", memberByNickName);

    } // memberNickNameSearch() 끝

    // 회원 등급 검색
    public DefaultRes<Page<UserSearchResponseDto>> memberGradeSearch(UserGrade userGrade, Pageable pageable) {
        Page<UserSearchResponseDto> memberByGrade = userQueryRepository.findByGrade(userGrade, pageable);

        if (memberByGrade.isEmpty()) { // 회원 Email 검색 시 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색 결과 없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색완료", memberByGrade);
    } // memberGradeSearch()끝



} // Class 끝
