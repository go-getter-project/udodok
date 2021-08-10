package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.manager.MemberJoinCountRequestDto;
import udodog.goGetterServer.model.dto.request.manager.MemberUpdateGradeRequestDto;
import udodog.goGetterServer.model.dto.request.user.UserFindEmailRequest;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.model.dto.response.manager.visuallization.MemberJoinVisuallizationResponseDto;
import udodog.goGetterServer.model.dto.response.user.UserFindEmailResponseDto;
import udodog.goGetterServer.model.entity.QUser;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QUser.user;
import static udodog.goGetterServer.model.entity.QUserConnection.userConnection;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Transactional
    public void updatePwd(String email, String pw) {

        JPAUpdateClause updateClause = new JPAUpdateClause(em, user);

        updateClause.where(user.email.eq(email))
                .set(user.password, pw)
                .execute();
    }

    public Optional<UserFindEmailResponseDto> findByUser(UserFindEmailRequest requestDto) {

        UserFindEmailResponseDto userEail =
                queryFactory
                        .select(Projections.fields(UserFindEmailResponseDto.class,
                                user.email))
                        .from(user)
                        .where(user.name.eq(requestDto.getName()), user.phoneNumber.eq(requestDto.getPhoneNumber()))
                        .fetchOne();
        return Optional.ofNullable(userEail);
    }

    // 전체 조회
    public Page<UserSearchResponseDto> findAll ( Pageable pageable ) { // 페이징 처리

        List<UserSearchResponseDto> userList = queryFactory
                .select(Projections.constructor(UserSearchResponseDto.class,
                        user.id,
                        user.email,
                        user.name,
                        user.phoneNumber,
                        user.nickName,
                        user.grade,
                        user.createdAt))
                .from(user)
                .where(user.grade.eq(UserGrade.USER).or(user.grade.eq(UserGrade.BLACK)))
                .orderBy(user.name.desc())
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userList.size());

        return new PageImpl<>(userList.subList(start, end), pageable, userList.size());

    } // findAll() 끝

    // Member 식별번호로 상세정보 조회
    public Optional<UserSearchResponseDto> findById (Long userId) {
        UserSearchResponseDto userSearchResponseDto = queryFactory.select(Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                                .where(user.id.eq(userId))
                                .fetchOne();

        return Optional.ofNullable(userSearchResponseDto);
    } // findById() 끝

    public Optional<User> findByUserId (Long userId) {
        User user = queryFactory.selectFrom(QUser.user)
                                .where(QUser.user.id.eq(userId))
                                .fetchOne();

        return Optional.ofNullable(user);

    } // findById()

    public Optional<User> findByUserGrade(UserGrade userGrade, Long userId) {

        User user = queryFactory.selectFrom(QUser.user)
                                .where(QUser.user.id.eq(userId))
                                .fetchOne();

        return Optional.ofNullable(user);

    } // findByUserGrade() 끝

    //회원 등급 변경 Method
    @Transactional
    public void updateMemberGrade(MemberUpdateGradeRequestDto memberUpdateGradeRequestDto, Long userId) {

        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, user);

        jpaUpdateClause.where(user.id.eq(userId))
                       .set(user.grade, memberUpdateGradeRequestDto.getUserGrade())
                        .execute();
    } // updateMemberGrade() 끝

    // Black 회원 강제 탈퇴 Method
    @Transactional
    public void blackMemberWithdrawal(Long userId, UserGrade userGrade) {

        JPADeleteClause deleteClause = new JPADeleteClause(em, user);

        deleteClause.where(user.id.eq(userId)).execute();
    } // blackMemberWithdrawal() 끝

    // 회원 가입일 전체 조회

    public List<MemberJoinVisuallizationResponseDto> findByCreatedAt(String year) {
        StringTemplate formattedDate = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , user.createdAt
                , ConstantImpl.create("%m"));
        List<MemberJoinVisuallizationResponseDto> searchJoinCount =
                queryFactory
                        .select(Projections.constructor(MemberJoinVisuallizationResponseDto.class,
                                formattedDate.as("month"),
                                user.createdAt.count()
                        ))
                        .from(user)
                        .where(user.createdAt.year().eq(Integer.parseInt(year)))
                        .groupBy(formattedDate)
                        .orderBy(user.createdAt.asc())
                        .fetch();
        Map<String, Long> map = new HashMap<>();

        for (MemberJoinVisuallizationResponseDto month : searchJoinCount) { // Query문을 통해 조회 된 값을 month에 하나씩 넣으면서 반복
            map.put(month.getMonth(), month.getCount());
        } // for-each문 끝

        for(int i = 1; i <= 12; i++) {      // 12개월을 기준으로 i를 증가 시켜 반복
            String key = String.format("%02d", i);

            if (map.get(String.valueOf(key)) == null) { // map안에 넣은 값이 없다면?
                // 해당 월의 값을 0으로 넣어 값이 없는 달의 값도 출력 되게 한다.
                MemberJoinVisuallizationResponseDto responseDto = new MemberJoinVisuallizationResponseDto(key, 0L);
                searchJoinCount.add(responseDto);               // 0으로 된 Value를 가진 key를 다시 query문에 넣어 값이 0이 나오도록 한다.
            } // if문 끝
        } // for 문 끝

        return searchJoinCount;
    } // findByCreatedAt() 끝

    // ######################## 검색 기능 ###########################

    // 회원 이름으로 검색
    public Page<UserSearchResponseDto> findByName(String name, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                                .where(user.name.contains(name))
                                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());
    } // findByName()끝

    // 회원 Email로 검색
    public Page<UserSearchResponseDto> findByEmail(String email, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                .where(user.email.contains(email))
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());

    } // findByEmail() 끝

    // 회원 별명 검색
    public Page<UserSearchResponseDto> findByNickName(String nickName, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                .where(user.nickName.contains(nickName))
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());
    } // findByNickName()끝

    // 회원 등급 검색
    public Page<UserSearchResponseDto> findByGrade(UserGrade userGrade, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                .where(user.grade.eq(userGrade))
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());

    } // findByGrade() 끝

    @Transactional
    public void updatePassword(String socialEmail, String access_token) {

        JPAUpdateClause updateClause = new JPAUpdateClause(em, user);

        updateClause
                .where(user.email.eq(socialEmail))
                .set(user.password, access_token)
                .execute();
    }

    public User findBySocialEmail(String socialEmail){
        User userInfo =
                queryFactory
                        .selectFrom(user)
                        .innerJoin(user.userConnection, userConnection)
                        .fetchJoin()
                        .where(user.email.eq(socialEmail))
                        .fetchOne();

        return userInfo;
    }
} // Class 끝
