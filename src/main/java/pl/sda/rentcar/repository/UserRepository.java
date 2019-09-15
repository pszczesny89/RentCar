package pl.sda.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.rentcar.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByLogin(String login);
}
