package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.MessageRoom;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
}
