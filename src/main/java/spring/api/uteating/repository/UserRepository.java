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
    @Query("select u from User u where u.username = :username")
    public User getUserByUsernameParam(@Param("username") String username);

    User getUserByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
}
