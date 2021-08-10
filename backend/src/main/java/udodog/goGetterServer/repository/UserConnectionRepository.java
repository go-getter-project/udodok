package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import udodog.goGetterServer.model.entity.UserConnection;

import java.util.Optional;

public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {

    @Query("Select uc.email From UserConnection uc WHERE uc.email= :email")
    Optional<String> findByEmail(@Param("email") String email);
}
