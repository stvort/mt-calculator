package ru.otus.mtcalculator.resopsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.mtcalculator.model.UserResult;

public interface UserResultRepository extends JpaRepository<UserResult, Long> {
}
