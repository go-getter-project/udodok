package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.MessageRoomJoin;

import java.util.List;

public interface MessageRoomJoinRepository extends JpaRepository<MessageRoomJoin, Long> {

    List<MessageRoomJoin> findAllByUserId(Long userId);
}
