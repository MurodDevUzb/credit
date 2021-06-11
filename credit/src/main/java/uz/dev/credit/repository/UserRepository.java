package uz.dev.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dev.credit.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
}
