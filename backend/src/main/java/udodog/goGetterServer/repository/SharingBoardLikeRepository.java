package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoardLike;

import java.util.Optional;

@Repository
public interface SharingBoardLikeRepository extends JpaRepository<SharingBoardLike,Long> {

    Optional<SharingBoardLike> findByUserIdAndSharingBoardId(Long userId, Long boardId);
}
