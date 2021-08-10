package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
