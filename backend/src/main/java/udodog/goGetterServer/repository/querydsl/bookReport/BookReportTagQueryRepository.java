package udodog.goGetterServer.repository.querydsl.bookReport;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.bookreport.BookreportUpdateRequestDto;
import udodog.goGetterServer.model.dto.request.bookreporttag.BookReportTagUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.bookreporttag.BookReportTagResponseDto;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.model.entity.QBookReportTag;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QBookReport.bookReport;
import static udodog.goGetterServer.model.entity.QBookReportTag.bookReportTag;
import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class BookReportTagQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    // 독서 기록 내용 삭제 시 Tag 삭제 처리
    @Transactional
    public void deleteByBookReportId(Long bookReportId) {

        JPADeleteClause deleteClause = new JPADeleteClause(entityManager, bookReportTag);

        deleteClause.where(bookReportTag.bookReport.bookReportId.eq(bookReportId)).execute();

    } // deleteByBookReportId() 끝

    // 독서 기록 번호 기반 Tag 찾기
    public Optional<BookReportTag> findByBookReportId(Long bookReportId) {

        BookReportTag bookReportTag = jpaQueryFactory.select(QBookReportTag.bookReportTag)
                .where(QBookReportTag.bookReportTag.bookReport.bookReportId.eq(bookReportId))
                .fetchOne();

        return Optional.ofNullable(bookReportTag);

    } // findByBookReportId() 끝

    // 독서 기록 Tag 조회
    public Page<BookReportTagResponseDto> findAllWithFetchJoin(Pageable pageable) {

        List<BookReportTagResponseDto> bookReportTagResponseDtos = jpaQueryFactory
                .select(Projections.constructor(BookReportTagResponseDto.class,
                    bookReportTag.bookReportTagId,
                    bookReport.bookReportId,
                    bookReportTag.tagName))
                .from(bookReportTag)
                .innerJoin(bookReportTag.bookReport, bookReport)
                .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), bookReportTagResponseDtos.size());

        return new PageImpl<>(bookReportTagResponseDtos.subList(start, end), pageable, bookReportTagResponseDtos.size());
    } // findAllWithFetchJoin() 끝

    // Tag 번호 검색
    public Optional<BookReportTag> findByBookReportTagId(Long bookReportTagId) {

        BookReportTag bookReportTag = jpaQueryFactory.selectFrom(QBookReportTag.bookReportTag)
                                                     .innerJoin(QBookReportTag.bookReportTag.bookReport, bookReport)
                                                     .fetchJoin()
                                                     .where(QBookReportTag.bookReportTag.bookReportTagId.eq(bookReportTagId))
                                                     .fetchOne();

        return Optional.ofNullable(bookReportTag);

    } // findByBookReportTagId() 끝

    @Transactional
    public void bookReportTagUpdate (BookreportUpdateRequestDto bookreportUpdateRequestDto, Long bookReportId) {    // 독서 기록 Tag 번호가 같다면 수정

        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(entityManager, bookReportTag);

        jpaUpdateClause.where(bookReportTag.bookReport.bookReportId.eq(bookReportId))
                       .set(bookReportTag.tagName, bookreportUpdateRequestDto.getTagName())
                       .execute();
    } // bookReportTagUpdate() 끝

    @Transactional
    public void deleteByBookReportTagId(Long id, Long bookReportId) {   // Tag 번호와 독서 기록 번호 일치 시 삭제

        JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager, bookReportTag);

        jpaDeleteClause.where(bookReportTag.bookReportTagId.eq(id), bookReportTag.bookReport.bookReportId.eq(bookReportId))
                       .execute();

    } // deleteByBookReportTagId() 끝

    public Optional<BookReport> findById(Long bookReportId) {

        BookReport report = jpaQueryFactory.selectFrom(bookReport)
                .innerJoin(bookReport.user, user)
                .where(bookReport.bookReportId.eq(bookReportId))
                .fetchOne();

        return Optional.ofNullable(report);

    } // findById() 끝
} // Class 끝
