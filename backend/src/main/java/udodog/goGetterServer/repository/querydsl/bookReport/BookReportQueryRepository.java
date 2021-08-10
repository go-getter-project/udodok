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
import udodog.goGetterServer.model.dto.response.bookreport.BookReportDetailResponseDto;
import udodog.goGetterServer.model.dto.response.bookreport.BookreportResponseDto;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.QBookReport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QBookReport.bookReport;
import static udodog.goGetterServer.model.entity.QBookReportTag.bookReportTag;
import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class BookReportQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    // 전체 조회
    public Page<BookreportResponseDto> findAllWithFetchJoin(Pageable pageable, Long userId) { // 페이징 처리

        List<BookreportResponseDto> reportList = jpaQueryFactory
                .select(Projections.constructor(BookreportResponseDto.class,
                        bookReport.bookReportId,
                        bookReport.bookName,
                        bookReport.title,
                        bookReport.createdAt,
                        bookReportTag.tagName))
                .from(bookReport)
                .innerJoin(bookReportTag).on(bookReportTag.bookReport.bookReportId.eq(bookReport.bookReportId))
                .where(bookReport.user.id.eq(userId))
                .orderBy(bookReport.createdAt.desc())
                .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), reportList.size());

        return new PageImpl<>(reportList.subList(start, end), pageable, reportList.size());
    } // reportList끝


    public Optional<BookReportDetailResponseDto> findByBookReportId(Long bookReportId){
        BookReportDetailResponseDto result = jpaQueryFactory
                .select(Projections.constructor(BookReportDetailResponseDto.class,
                        bookReport.bookName,
                        bookReport.title,
                        bookReport.content,
                        bookReport.createdAt,
                        bookReportTag.tagName))
                .from(bookReport)
                .innerJoin(bookReportTag).on(bookReportTag.bookReport.bookReportId.eq(bookReport.bookReportId))
                .where(bookReport.bookReportId.eq(bookReportId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    // 독서 기록 수정
    @Transactional
    public void updateBookReport(BookreportUpdateRequestDto updateRequestDto, Long bookReportId, Long userId) {

        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, bookReport);

        updateClause.where(bookReport.bookReportId.eq(bookReportId), bookReport.user.id.eq(userId))
                    .set(bookReport.bookName, updateRequestDto.getBookName())
                    .set(bookReport.content, updateRequestDto.getContent())
                    .execute();
    } // updateBookReport() 끝

    // 독서 기록 삭제
    @Transactional
    public void deleteBookReport(Long bookReportId, Long id) {

        JPADeleteClause deleteClause = new JPADeleteClause(entityManager, bookReport);

        deleteClause.where(bookReport.bookReportId.eq(bookReportId), bookReport.user.id.eq(id)).execute();

    } // deleteBookReport() 끝


    // ######################## 검색 기능 ###########################

    // 제목 검색
    public Page<BookreportResponseDto> findByTitle(String title, Pageable pageable) {

        List<BookreportResponseDto> bookreportResponseDtos = jpaQueryFactory.select(Projections.constructor(BookreportResponseDto.class,
                bookReport.bookReportId,
                user.nickName,
                bookReport.bookName,
                bookReport.title,
                bookReport.createdAt))
            .from(bookReport)
            .innerJoin(bookReport.user, user)
            .where(bookReport.title.contains(title))
            .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), bookreportResponseDtos.size());

        return new PageImpl<>(bookreportResponseDtos.subList(start, end), pageable, bookreportResponseDtos.size());
    } // findByTitle() 끝


    public Optional<BookReport> findByBookReportId(Long bookReportId, Long userId) {

        BookReport report = jpaQueryFactory.selectFrom(QBookReport.bookReport)
                                .where(QBookReport.bookReport.bookReportId.eq(bookReportId), QBookReport.bookReport.user.id.eq(userId))
                                .innerJoin(QBookReport.bookReport.user, user)
//                                .fetchJoin()
                                .fetchOne();

        return Optional.ofNullable(report);

    } // findByBookReportId() 끝
} // Class 끝
