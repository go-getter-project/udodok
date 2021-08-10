package udodog.goGetterServer.service.bookreport;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.bookreport.BookreportInsertRequestDto;
import udodog.goGetterServer.model.dto.request.bookreport.BookreportUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.bookreport.BookReportDetailResponseDto;
import udodog.goGetterServer.model.dto.response.bookreport.BookreportResponseDto;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.BookReportRepository;
import udodog.goGetterServer.repository.BookReportTagRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.bookReport.BookReportQueryRepository;
import udodog.goGetterServer.repository.querydsl.bookReport.BookReportTagQueryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookreportService {

    private final UserRepository userRepository;
    private final BookReportRepository bookReportRepository;
    private final BookReportTagRepository bookReportTagRepository;
    private final BookReportTagQueryRepository bookReportTagQueryRepository;
    private final BookReportQueryRepository bookReportQueryRepository;

    // 전체 조회
    public DefaultRes<Page<BookreportResponseDto>> searchBookReportList(Pageable pageable, Long userId) { // 페이징 처리

        Page<BookreportResponseDto> bookReportPage = bookReportQueryRepository.findAllWithFetchJoin(pageable, userId);

        if (bookReportPage.getTotalElements() == 0) { // bookreport의 내용이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");

        } else {                                      // bookreport의 내용이 있다면?
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", bookReportPage, new Pagination(bookReportPage));
        } // if-else 끝

    } // searchBookReportList() 끝


    // 독서 기록 등록 Method
    public DefaultRes insertReport(BookreportInsertRequestDto bookreportInsertRequestDto, Long userId) {

        Optional<User> user = userRepository.findById(userId);
        
        if ( bookreportInsertRequestDto == null ) { // 독서 기록 입력 값이 없다면?

            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");

        }                                    // 독서 기록 입력 값이 있다면?
            Optional<BookReport> bookReport = Optional.ofNullable( bookReportRepository.save(bookreportInsertRequestDto.toEntity(bookreportInsertRequestDto, user)) );

            bookReportTagRepository.save(BookReportTag.builder().bookReport(bookReport.get()).tagName(bookreportInsertRequestDto.getBookReportTag()).build());

            return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
        
    } // insertReport() 끝

    // 독서 기록 상세 조회 Method
    public DefaultRes<BookReportDetailResponseDto> viewDetailBookReport(Long bookReportId) {

        Optional<BookReportDetailResponseDto> bookReportOptional = bookReportQueryRepository.findByBookReportId(bookReportId);


        if (bookReportOptional.isEmpty()) { // 독서 기록이 비어 있다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");

        } else {                            // 독서 기록 내용이 있다면?

            return bookReportOptional.map(bookReport -> DefaultRes.response(HttpStatus.OK.value(), "조회성공", bookReport))
                    .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "조회실패"));
        } // if-else 끝
    } // viewDetailBookReport() 끝

    // 독서 기록 수정 Method
    public DefaultRes updateReport(BookreportUpdateRequestDto updateRequestDto, Long bookReportId, Long userId) {  // updateDto, 글번호, 유저 ID
        Optional<BookReport> optionalBookReport = bookReportQueryRepository.findByBookReportId(bookReportId, userId);

        if (optionalBookReport.isEmpty()) { // 대상 게시물이 없다면?
            DefaultRes.response(HttpStatus.OK.value(), "내용없음");
        } // if문 끝

        return optionalBookReport.filter(bookReport -> bookReport.getUser().getId().equals(userId))
                .filter(bookReport -> bookReport.getBookReportId().equals(bookReportId))
                .map(bookReport -> {

                    bookReportQueryRepository.updateBookReport(updateRequestDto, bookReportId, userId);
                    bookReportTagQueryRepository.bookReportTagUpdate(updateRequestDto, bookReport.getBookReportId());

                    return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));

    } // updateReport() 끝

    // 독서 기록 삭제 Method
    public DefaultRes deleteReport(Long bookReportId, Long userId) {    // 게시글 번호, User 번호
        Optional<BookReport> bookReportOptional = bookReportQueryRepository.findByBookReportId(bookReportId, userId);

        if (bookReportOptional.isEmpty()) { // 대상 게시물이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "내용없음");
        } // if문 끝

        return bookReportOptional.filter(bookReport -> bookReport.getBookReportId().equals(bookReportId))
                .filter(bookReport -> bookReport.getUser().getId().equals(userId)).map(bookReport -> {
                    bookReportTagQueryRepository.deleteByBookReportId(bookReport.getBookReportId());
                    bookReportQueryRepository.deleteBookReport(bookReport.getBookReportId(), bookReport.getUser().getId());

                    return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
                }).orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "삭제실패"));

    } // deleteReport() 끝

    // ################################# 검색 기능 ####################################

    // 제목 검색
    public DefaultRes<Page<BookreportResponseDto>> titleSearch(String title, Pageable pageable) {

        Page<BookreportResponseDto> reportPage = bookReportQueryRepository.findByTitle(title, pageable);

        if (reportPage.isEmpty()) { // 검색 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색결과없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색결과있음", reportPage);

    } // titleSearch() 끝

} // Class 끝
