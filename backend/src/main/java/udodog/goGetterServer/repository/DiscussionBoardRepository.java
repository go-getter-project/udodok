package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.DiscussionBoard;

import java.util.Optional;

@Repository
public interface DiscussionBoardRepository extends JpaRepository<DiscussionBoard, Long> {

    @Query(value = "select d from DiscussionBoard d join fetch d.user",
            countQuery = "select count(d) from DiscussionBoard d")
    Page<DiscussionBoard> findAllWithFetchJoin(Pageable pageable);

    @Query(value = "select d from DiscussionBoard d join fetch d.user where d.id= :id")
    Optional<DiscussionBoard> findById(@Param("id") Long id);

    @Query(value = "select d from DiscussionBoard d join fetch d.user where title like %:title%",
            countQuery = "select count(d) from DiscussionBoard d")
    Page<DiscussionBoard> findByTitleContaining(@Param("title") String title, Pageable pageable);

    @Query(value = "select d from DiscussionBoard d join fetch d.user where content like %:content%",
            countQuery = "select count(d) from DiscussionBoard d")
    Page<DiscussionBoard> findByContentContaining(@Param("content") String content, Pageable pageable);

    @Query(value = "select d from DiscussionBoard d join fetch d.user where title like %:search% or content like %:search%",
            countQuery = "select count(d) from DiscussionBoard d")
    Page<DiscussionBoard> findByAllContaining(@Param("search") String search, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "delete from DiscussionBoard d where d.id = :id and d.user.id = :userId")
    void deleteById(@Param("id") Long id, @Param("userId") Long userId);
}
