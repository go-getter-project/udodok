package udodog.goGetterServer.service.nationwidelibraryinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.nationwidelibraryinfo.NationwideLibraryInfoInsertReqeustDto;
import udodog.goGetterServer.model.dto.request.nationwidelibraryinfo.NationwideLibraryInfoUpdateReqeustDto;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.NationwidelibraryInfoResponseDto;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.visualization.NationwidelibraryInfoVisualizationDto;
import udodog.goGetterServer.model.entity.NationwideLibraryInfo;
import udodog.goGetterServer.repository.NationwideLibraryInfoRepository;
import udodog.goGetterServer.repository.querydsl.nationwidelibraryInfo.NationwidelibraryInfoQueryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NationwidelibraryInfoService {

    private final NationwidelibraryInfoQueryRepository nationwidelibraryInfoQueryRepository;
    private final NationwideLibraryInfoRepository nationwideLibraryInfoRepository;

    // 전체 조회
    public DefaultRes<Page<NationwidelibraryInfoResponseDto>> searchLibraryInfoList(Pageable pageable) {

        Page<NationwidelibraryInfoResponseDto> libraryInfoAll = nationwidelibraryInfoQueryRepository.findAll(pageable);

        if (libraryInfoAll.getTotalElements() == 0) {   // 전국 도서관 입력 정보가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터 없음");
        } else {
            return DefaultRes.response(HttpStatus.OK.value(), "조회 성공", libraryInfoAll, new Pagination(libraryInfoAll));
        }

    } // searchLibraryInfoList() 끝

    // 상세 조회 Method
    public DefaultRes<NationwidelibraryInfoResponseDto> searchLibraryInfoDetail(Long nationwideLibraryInfolibraryInfoId) {

        Optional<NationwidelibraryInfoResponseDto> searchDetailById = nationwidelibraryInfoQueryRepository.findById(nationwideLibraryInfolibraryInfoId);

        if (searchDetailById.isEmpty()) { // 상세 조회를 원하는 도서관 정보가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터 없음");
        } else {
            return searchDetailById.map(librarySearch -> DefaultRes.response(HttpStatus.OK.value(), "조회 성공", librarySearch))
                    .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "조회 실패"));
        } // if - else 문 끝
    } // searchLibraryInfoDetail() 끝

    // 도서관 위치 정보 조회 관련 Method
    public DefaultRes<Page<NationwidelibraryInfoVisualizationDto>> getLibraryLocationInfo(Pageable pageable) {

        Page<NationwidelibraryInfoVisualizationDto> byLocation = nationwidelibraryInfoQueryRepository.findByLocation(pageable);

        if (byLocation.getTotalElements() == 0) { // 조회된 도서관 위치 정보가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터 없음");
        } else {
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", byLocation, new Pagination(byLocation));
        }

    } // getLibraryLocationInfo() 끝

    public DefaultRes insertLibraryInfo(NationwideLibraryInfoInsertReqeustDto infoInsertReqeustDto) {

        if (infoInsertReqeustDto == null) { // 도서관 정보 입력 값이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        } // if 문 끝

        nationwideLibraryInfoRepository.save(infoInsertReqeustDto.toEntity(infoInsertReqeustDto));

        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
    } // insertLibraryInfo() 끝

    // 전국 도서관 정보 수정 Method
    public DefaultRes updateLibraryInfo(NationwideLibraryInfoUpdateReqeustDto updateReqeustDto, Long nationwideLibraryInfolibraryInfoId) {

        Optional<NationwideLibraryInfo> nationwideLibraryInfo = nationwidelibraryInfoQueryRepository.findByLibraryId(nationwideLibraryInfolibraryInfoId);

        if (nationwideLibraryInfo.isEmpty()) { // 수정 대상 게시물이 없다면?
            DefaultRes.response(HttpStatus.OK.value(), "내용없음");
        } // if문 끝

        return nationwideLibraryInfo.filter(libraryInformation -> libraryInformation.getLibraryInfoId().equals(nationwideLibraryInfolibraryInfoId))
                                    .map(libraryInformation -> {
                                        nationwidelibraryInfoQueryRepository.updateLibraryInfo(updateReqeustDto, libraryInformation.getLibraryInfoId());

                                        return DefaultRes.response(HttpStatus.OK.value(), "수정 성공");
                                    }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정 실패"));
    } // updateLibraryInfo() 끝
} // Class 끝
