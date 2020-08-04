package ru.otus.mtcalculator.resopsitories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mtcalculator.model.*;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с результатами тренировки должен ")
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class UserScoreRepositoryTest {

    @Autowired
    private UserScoreRepository scoreRepository;

    @DisplayName("корректно сохранять и загружать сущность результата")
    @Test
    void saveTest(){
        UserScore s = new UserScore(0, new UserBrief(1), 100,
                new TrainingSessionBrief(UUID.randomUUID().toString()),
                100, new Timestamp(System.currentTimeMillis()));
        UserScore savedScore = scoreRepository.save(s);

        assertThat(savedScore).isNotNull().isEqualToIgnoringGivenFields(s, "id")
                .matches(sr -> sr.getId() > 0);

        Optional<UserScore> actualResult = scoreRepository.findById(savedScore.getId());
        assertThat(actualResult).isNotEmpty().get().isEqualToComparingFieldByField(savedScore);

    }

}