package ru.otus.mtcalculator.resopsitories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mtcalculator.model.EquationBrief;
import ru.otus.mtcalculator.model.TrainingSessionBrief;
import ru.otus.mtcalculator.model.UserBrief;
import ru.otus.mtcalculator.model.UserResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с результатами решения примеров должен ")
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class UserResultRepositoryTest {

    @Autowired
    private UserResultRepository resultRepository;

    @DisplayName("корректно сохранять и загружать сущность результата")
    @Test
    void saveTest() {
        UserResult r = new UserResult(0, new UserBrief(1), new EquationBrief(1),
                new TrainingSessionBrief(UUID.randomUUID().toString()), true, new Timestamp(System.currentTimeMillis()));
        UserResult savedResult = resultRepository.save(r);

        assertThat(savedResult).isNotNull().isEqualToIgnoringGivenFields(r, "id")
                .matches(sr -> sr.getId() > 0);

        Optional<UserResult> actualResult = resultRepository.findById(savedResult.getId());
        assertThat(actualResult).isNotEmpty().get().isEqualToComparingFieldByField(savedResult);

    }

}