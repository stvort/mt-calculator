package ru.otus.mtcalculator.resopsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.mtcalculator.model.UserScore;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
}
