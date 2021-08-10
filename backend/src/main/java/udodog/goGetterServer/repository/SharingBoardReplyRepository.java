package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoardReply;

import java.util.List;
import java.util.Optional;

@Repository
public interface SharingBoardReplyRepository extends JpaRepository<SharingBoardReply,Long> {

    void deleteBySharingBoardId(Long id);


    @Query(value = "select sr from SharingBoardReply sr join fetch sr.sharingBoard board join fetch board.user where board.id= :boardId",
            countQuery = "select count(sr) from SharingBoardReply")
    Page<SharingBoardReply> findAll(@Param("boardId") Long boardId, Pageable pageable);

}
