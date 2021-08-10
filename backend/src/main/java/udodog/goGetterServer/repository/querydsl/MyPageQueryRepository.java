package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.mypage.MyPageRequestDto;
import udodog.goGetterServer.model.dto.response.mypage.MyPageResponseDto;
import udodog.goGetterServer.model.entity.User;

import javax.persistence.EntityManager;

import java.util.Optional;

import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class MyPageQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    // 유저 번호로 유저 상세정보 조회
    public Optional<MyPageResponseDto> findById(Long userId) {

        MyPageResponseDto responseDto =
                queryFactory
                        .select(Projections.fields(MyPageResponseDto.class,
                                user.id,
                                user.email,
                                user.name,
                                user.phoneNumber,
                                user.nickName,
                                user.password))
                        .from(user)
                        .where(user.id.eq(userId))
                        .fetchOne();

        return Optional.ofNullable(responseDto);
    }

    // 유저 번호로 유저 정보 조회
    public Optional<User> findByUserId(Long userId){

        User userInfo =
                queryFactory
                        .selectFrom(user)
                        .where(user.id.eq(userId))
                        .fetchOne();

        return Optional.ofNullable(userInfo);
    }

    // 닉네임, 비밀번호 수정
    @Transactional
    public void modifyUserInfo(MyPageRequestDto requestDto, Long userId) {

        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, user);

        jpaUpdateClause.where(user.id.eq(userId))
                .set(user.nickName, requestDto.getNickName())
                .set(user.password, requestDto.getPassword())
                .execute();
    }
}
