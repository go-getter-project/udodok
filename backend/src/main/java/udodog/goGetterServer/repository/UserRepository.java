package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import udodog.goGetterServer.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user.email FROM User user WHERE user.email = :email")
    Optional<String> findByEmail(@Param("email") String email);

    @Query("SELECT user FROM User user WHERE user.email = :email and user.password =:password")
    Optional<User> findByUser(@Param("email") String email, @Param("password") String password);


}
