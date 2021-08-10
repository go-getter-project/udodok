package udodog.goGetterServer.repository.querydsl.nationwidelibraryInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.nationwidelibraryinfo.NationwideLibraryInfoUpdateReqeustDto;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.NationwidelibraryInfoResponseDto;
import udodog.goGetterServer.model.dto.response.nationwidelibraryinfo.visualization.NationwidelibraryInfoVisualizationDto;
import udodog.goGetterServer.model.entity.NationwideLibraryInfo;
import udodog.goGetterServer.model.entity.QNationwideLibraryInfo;

import static udodog.goGetterServer.model.entity.QNationwideLibraryInfo.nationwideLibraryInfo;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class NationwidelibraryInfoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    // 전체 조회
    public Page<NationwidelibraryInfoResponseDto> findAll (Pageable pageable ) {    // 페이징 처리하여 조회

        List<NationwidelibraryInfoResponseDto> libraryInfoList = jpaQueryFactory
                .select(Projections.constructor(NationwidelibraryInfoResponseDto.class,
                        nationwideLibraryInfo.libraryInfoId,
                        nationwideLibraryInfo.libraryName,
                        nationwideLibraryInfo.cityName,
                        nationwideLibraryInfo.sigunguName,
                        nationwideLibraryInfo.libraryType,
                        nationwideLibraryInfo.redDay,
                        nationwideLibraryInfo.weekdayStartTime,
                        nationwideLibraryInfo.weekdayEndTime,
                        nationwideLibraryInfo.saturdayStartTime,
                        nationwideLibraryInfo.saturdayEndTime,
                        nationwideLibraryInfo.publicHolidayStartTime,
                        nationwideLibraryInfo.publicHolidayEndTime,
                        nationwideLibraryInfo.numberOfReadingSeats,
                        nationwideLibraryInfo.numberOfBooks,
                        nationwideLibraryInfo.numberOfPublications,
                        nationwideLibraryInfo.numberOfNotBooks,
                        nationwideLibraryInfo.loanableCount,
                        nationwideLibraryInfo.loanableDayTime,
                        nationwideLibraryInfo.roadAddress,
                        nationwideLibraryInfo.operationInstitution,
                        nationwideLibraryInfo.phoneNumber,
                        nationwideLibraryInfo.website,
                        nationwideLibraryInfo.dataInputDay))
                .from(nationwideLibraryInfo)
                .orderBy(nationwideLibraryInfo.libraryName.desc())
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), libraryInfoList.size());

        return new PageImpl<>(libraryInfoList.subList(start, end), pageable, libraryInfoList.size());

    } // findAll() 끝

    // 도서관 식별번호로 상세 정보 조회
    public Optional<NationwidelibraryInfoResponseDto> findById(Long nationwideLibraryInfolibraryInfoId) {

        NationwidelibraryInfoResponseDto nationwidelibraryInfoResponseDto = jpaQueryFactory
                .select(Projections.constructor(NationwidelibraryInfoResponseDto.class,
                        nationwideLibraryInfo.libraryInfoId,
                        nationwideLibraryInfo.libraryName,
                        nationwideLibraryInfo.cityName,
                        nationwideLibraryInfo.sigunguName,
                        nationwideLibraryInfo.libraryType,
                        nationwideLibraryInfo.redDay,
                        nationwideLibraryInfo.weekdayStartTime,
                        nationwideLibraryInfo.weekdayEndTime,
                        nationwideLibraryInfo.saturdayStartTime,
                        nationwideLibraryInfo.saturdayEndTime,
                        nationwideLibraryInfo.publicHolidayStartTime,
                        nationwideLibraryInfo.publicHolidayEndTime,
                        nationwideLibraryInfo.numberOfReadingSeats,
                        nationwideLibraryInfo.numberOfBooks,
                        nationwideLibraryInfo.numberOfPublications,
                        nationwideLibraryInfo.numberOfNotBooks,
                        nationwideLibraryInfo.loanableCount,
                        nationwideLibraryInfo.loanableDayTime,
                        nationwideLibraryInfo.roadAddress,
                        nationwideLibraryInfo.operationInstitution,
                        nationwideLibraryInfo.phoneNumber,
                        nationwideLibraryInfo.website,
                        nationwideLibraryInfo.dataInputDay)).from(nationwideLibraryInfo)
                                                            .where(nationwideLibraryInfo.libraryInfoId.eq(nationwideLibraryInfolibraryInfoId))
                                                            .fetchOne();

        return Optional.ofNullable(nationwidelibraryInfoResponseDto);
    } // findById() 끝

    public Page<NationwidelibraryInfoVisualizationDto> findByLocation(Pageable pageable) {  // 페이징 처리

        List<NationwidelibraryInfoVisualizationDto> libraryLocationList = jpaQueryFactory
                .select(Projections.constructor(NationwidelibraryInfoVisualizationDto.class,
                        nationwideLibraryInfo.libraryInfoId,
                        nationwideLibraryInfo.libraryName,
                        nationwideLibraryInfo.libraryType,
                        nationwideLibraryInfo.redDay,
                        nationwideLibraryInfo.weekdayStartTime,
                        nationwideLibraryInfo.weekdayEndTime,
                        nationwideLibraryInfo.saturdayStartTime,
                        nationwideLibraryInfo.saturdayEndTime,
                        nationwideLibraryInfo.publicHolidayStartTime,
                        nationwideLibraryInfo.publicHolidayEndTime,
                        nationwideLibraryInfo.roadAddress,
                        nationwideLibraryInfo.phoneNumber,
                        nationwideLibraryInfo.website,
                        nationwideLibraryInfo.latitude,
                        nationwideLibraryInfo.longtitude)).from(nationwideLibraryInfo)
                                                          .where(nationwideLibraryInfo.roadAddress.eq(nationwideLibraryInfo.roadAddress))
                                                          .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), libraryLocationList.size());

        return new PageImpl<>(libraryLocationList.subList(start, end), pageable, libraryLocationList.size());

    } // findByLocation() 끝

    public Optional<NationwideLibraryInfo> findByLibraryId(Long nationwideLibraryInfolibraryInfoId) {

        NationwideLibraryInfo nationwideLibraryInfo = jpaQueryFactory.selectFrom(QNationwideLibraryInfo.nationwideLibraryInfo)
                                                                     .where(QNationwideLibraryInfo.nationwideLibraryInfo.libraryInfoId.eq(nationwideLibraryInfolibraryInfoId))
                                                                     .fetchOne();

        return Optional.ofNullable(nationwideLibraryInfo);

    } // findByLibraryId() 끝

    @Transactional
    public void updateLibraryInfo(NationwideLibraryInfoUpdateReqeustDto updateReqeustDto, Long libraryInfoId) {  // 도서관 정보 식별 번호가 같다면 수정

        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(entityManager, nationwideLibraryInfo);

        jpaUpdateClause.where(nationwideLibraryInfo.libraryInfoId.eq(libraryInfoId))
                        .set(nationwideLibraryInfo.libraryName, updateReqeustDto.getLibraryName())
                        .set(nationwideLibraryInfo.cityName, updateReqeustDto.getCityName())
                        .set(nationwideLibraryInfo.sigunguName, updateReqeustDto.getSigunguName())
                        .set(nationwideLibraryInfo.libraryType, updateReqeustDto.getLibraryType())
                        .set(nationwideLibraryInfo.redDay, updateReqeustDto.getRedDay())
                        .set(nationwideLibraryInfo.weekdayStartTime, updateReqeustDto.getWeekdayStartTime())
                        .set(nationwideLibraryInfo.weekdayEndTime, updateReqeustDto.getWeekdayEndTime())
                        .set(nationwideLibraryInfo.saturdayStartTime, updateReqeustDto.getSaturdayStartTime())
                        .set(nationwideLibraryInfo.saturdayEndTime, updateReqeustDto.getSaturdayEndTime())
                        .set(nationwideLibraryInfo.publicHolidayStartTime, updateReqeustDto.getPublicHolidayStartTime())
                        .set(nationwideLibraryInfo.publicHolidayEndTime, updateReqeustDto.getPublicHolidayEndTime())
                        .set(nationwideLibraryInfo.numberOfReadingSeats, updateReqeustDto.getNumberOfReadingSeats())
                        .set(nationwideLibraryInfo.numberOfBooks, updateReqeustDto.getNumberOfBooks())
                        .set(nationwideLibraryInfo.loanableCount, updateReqeustDto.getLoanableCount())
                        .set(nationwideLibraryInfo.loanableDayTime, updateReqeustDto.getLoanableDayTime())
                        .set(nationwideLibraryInfo.roadAddress, updateReqeustDto.getRoadAddress())
                        .set(nationwideLibraryInfo.operationInstitution, updateReqeustDto.getOperationInstitution())
                        .set(nationwideLibraryInfo.phoneNumber, updateReqeustDto.getPhoneNumber())
                        .set(nationwideLibraryInfo.website, updateReqeustDto.getWebsite())
                        .set(nationwideLibraryInfo.latitude, updateReqeustDto.getLatitude())
                        .set(nationwideLibraryInfo.longtitude, updateReqeustDto.getLongtitude())
                        .set(nationwideLibraryInfo.dataInputDay, updateReqeustDto.getDataInputDay())
                        .execute();
    } // updateLibraryInfo() 끝

} // Class 끝
