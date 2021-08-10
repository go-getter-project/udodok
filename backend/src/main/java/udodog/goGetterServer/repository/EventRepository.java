package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
