package ru.kortez.test.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kortez.test.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
