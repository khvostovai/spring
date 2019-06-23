package ru.kortez.test.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kortez.test.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
