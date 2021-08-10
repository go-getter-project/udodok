package udodog.goGetterServer.controller.api.manager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.manager.ManagerConverter;
import udodog.goGetterServer.model.converter.manager.ManagerListConverter;
import udodog.goGetterServer.model.converter.manager.ManagerPagingConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.manager.MemberJoinCountRequestDto;
import udodog.goGetterServer.model.dto.request.manager.MemberUpdateGradeRequestDto;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.model.dto.response.manager.visuallization.MemberJoinVisuallizationResponseDto;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.service.manager.UserSearchService;

import java.util.List;

@Api(tags = {"관리자 회원 관리 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class UserManagementController {

    private final UserSearchService userSearchService;
    private final ManagerConverter managerConverter;
    private final ManagerPagingConverter managerPagingConverter;
    private final ManagerListConverter listConverter;

    @ApiOperation(value = "회원 전체 조회 API", notes = "관리자 Page 회원 전체 목록 조회 API")
    @ApiResponses(value = { @ApiResponse(code=200, message = "1. 조회 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})

    // 전체 조회 관련 Method
    @GetMapping("/user/list")
    public ResponseEntity<EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> totalMemberFindAll (@PageableDefault(size = 20)Pageable pageable) { //  페이지당 20개씩 출력

        return new ResponseEntity<>(managerPagingConverter.toModel( userSearchService.searchUserList( pageable )), HttpStatus.OK);
    } // totalMemberFindAll() 끝

    @ApiOperation(value = "Member 상세 조회 API", notes = "Member 상세 조회 API")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "1. 상세보기성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러") })

    // 회원 정보 상세보기 관련 Method
    @GetMapping("/user/detail/{userId}")
    public ResponseEntity<EntityModel<DefaultRes<UserSearchResponseDto>>> memberDetail( @PathVariable("userId") Long userId ) {
        return new ResponseEntity<>(managerConverter.toModel(userSearchService.getMemberDetail(userId)), HttpStatus.OK);
    } // memberDetail()끝

    @ApiOperation( value = "회원 등급 변경", notes = "관리자 페이지의 회원 등급 변경 API" )
    @ApiResponses( value = { @ApiResponse( code=200, message = "1. 변경성공 \t\n 2. 변경실패 \t\n 3. 토큰에러" )})

    // 회원 등급 변경 Method
    @PatchMapping("/user/change-grade/{userId}")
    public ResponseEntity<EntityModel<DefaultRes<MemberUpdateGradeRequestDto>>> updateMemberGrade (@PathVariable("userId") Long userId, @RequestBody MemberUpdateGradeRequestDto updateRequestDto) {

        return new ResponseEntity<>(managerConverter.toModel(userSearchService.updateMemberGrade(userId, updateRequestDto)), HttpStatus.OK);
    } // updateMemberGrade()끝

    @ApiOperation( value = "Black 회원 강제 탈퇴", notes = "관리자 페이지의 Black 회원 강제 탈퇴 API" )
    @ApiResponses( value = { @ApiResponse( code=200, message = "1. 강제탈퇴 성공 \t\n 2. 강제탈퇴 실패 \t\n 3. 토큰에러" )})

    // Black 회원 강제 탈퇴 Method
    @DeleteMapping("/bkuser/delete/{userId}")
    public ResponseEntity<EntityModel<DefaultRes<UserSearchResponseDto>>> blackMemberWithdrawal (@PathVariable("userId") Long userId, UserGrade userGrade) {

        return new ResponseEntity<>(managerConverter.toModel(userSearchService.blackMemberWithdrawal(userId, userGrade)), HttpStatus.OK);
    } // blackMemberWithdrawal() 끝

    @ApiOperation( value = "월별 회원가입 수 시각화", notes = "관리자 페이지의 월별 회원가입 수 시각화 API" )
    @ApiResponses( value = { @ApiResponse( code=200, message = "1. 조회성공 \t\n 2. 조회실패 \t\n 3. 토큰에러" )})

    // 월 별 회원가입수 시각화 값 전달 Method
    @GetMapping("/join-visuallization")
    public ResponseEntity<EntityModel<DefaultRes<List<MemberJoinVisuallizationResponseDto>>>> memberJoingCount(@RequestParam("year") String year) {
        return new ResponseEntity<>(listConverter.toModel(userSearchService.memberJoinCount(year)), HttpStatus.OK);
    } // countMemberJoin() 끝


    // ##################################### 검색 기능 #####################################

    // 회원 이름으로 검색
    @GetMapping("/user/name-search/{name-search}")
    public ResponseEntity<EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> nameSearch (@PathVariable("name-search") String name, @PageableDefault (sort = "name", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {

        return new ResponseEntity<>(managerPagingConverter.toModel(userSearchService.memberNameSearch(name, pageable)), HttpStatus.OK);
    } // nameSearch()끝

    // 회원 Email 검색
    @GetMapping("/user/email-search/{email-search}")
    public ResponseEntity<EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> emailSearch (@PathVariable("email-search") String email, @PageableDefault (sort = "email", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {

        return new ResponseEntity<>(managerPagingConverter.toModel(userSearchService.memberEmailSearch(email, pageable)), HttpStatus.OK);
    } // emailSearch() 끝

    // 회원 NickName 검색
    @GetMapping("/user/nick-search/{nickname-search}")
    public ResponseEntity<EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> nickNameSearch (@PathVariable("nickname-search") String nickName, @PageableDefault (sort = "nickName", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {

        return new ResponseEntity<>(managerPagingConverter.toModel(userSearchService.memberNickNameSearch(nickName, pageable)), HttpStatus.OK);
    } // nickNameSearch() 끝

    // 회원 등급 검색
    @GetMapping("/user/grade-search/{grade-search}")
    public ResponseEntity<EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> gradeSearch (@PathVariable("grade-search") UserGrade userGrade, @PageableDefault (sort = "grade", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {

        return new ResponseEntity<>(managerPagingConverter.toModel(userSearchService.memberGradeSearch(userGrade, pageable)), HttpStatus.OK);
    } // gradeSearch() 끝

} // Class 끝
