package spring.api.uteating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.api.uteating.entity.Restaurant;
import spring.api.uteating.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
    User getUserByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}
