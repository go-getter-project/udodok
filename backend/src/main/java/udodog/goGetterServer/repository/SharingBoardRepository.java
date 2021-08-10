package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.repository.querydsl.sharingboard.SharingBoardExtension;
import java.util.Optional;

@Repository
public interface SharingBoardRepository extends JpaRepository<SharingBoard,Long>, SharingBoardExtension {

    @Query(value = "select board from SharingBoard board join fetch board.user left join fetch board.sharingBoardReplyList",
            countQuery = "select count(board) from SharingBoard board ")
    Page<SharingBoard> findAll(Pageable pageable);


    @Query(value = "select board from SharingBoard board join fetch board.user left join fetch board.sharingBoardReplyList where board.id = :boardId")
    Optional<SharingBoard> findById(@Param("boardId") Long id);



}
