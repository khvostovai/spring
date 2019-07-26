package ru.kortez.test.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kortez.test.domain.Message;
import ru.kortez.test.domain.Theme;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByTheme(Theme theme);
}
