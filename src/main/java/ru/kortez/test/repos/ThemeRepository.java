package ru.kortez.test.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kortez.test.domain.Theme;

public interface ThemeRepository extends CrudRepository<Theme, Long> {
}
