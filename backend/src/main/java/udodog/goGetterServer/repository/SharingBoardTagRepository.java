package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.SharingBoardTag;


@Repository
@Transactional
public interface SharingBoardTagRepository extends JpaRepository<SharingBoardTag, Long> {

    SharingBoardTag findBySharingBoardId(Long id);

    void deleteAllBySharingBoardId(Long boardId);
}
