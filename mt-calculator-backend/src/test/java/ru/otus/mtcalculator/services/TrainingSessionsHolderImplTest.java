package ru.otus.mtcalculator.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.TrainingSession;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сессионный кеш должен")
class TrainingSessionsHolderImplTest {

    @DisplayName("создавать корректно сессию")
    @Test
    void shouldCorrectCreateSession() {
        JwtUser jwtUser = new JwtUser(1L, "any", null, new ArrayList<>());
        TrainingSessionsHolder sessionsHolder = new TrainingSessionsHolderImpl(10);
        String sessionId = sessionsHolder.createSessionAndGetId(jwtUser, null);
        assertThat(sessionId).isNotNull().isNotEmpty().isNotBlank();

        Optional<TrainingSession> session = sessionsHolder.getSession(sessionId);
        assertThat(session).isNotEmpty()
                .get()
                .hasFieldOrPropertyWithValue("id", sessionId)
                .hasFieldOrPropertyWithValue("user", jwtUser)
                .matches((Predicate<TrainingSession>) s -> s.getStartTime() > System.currentTimeMillis() - 1000);
    }

    @DisplayName("удалять сессию после истечения времени ее жизни")
    @Test
    void shouldDeleteSessionAfterLifeTimeEstimate() throws InterruptedException {
        JwtUser jwtUser = new JwtUser(1L, "any", null, new ArrayList<>());
        TrainingSessionsHolder sessionsHolder = new TrainingSessionsHolderImpl(1);
        String sessionId = sessionsHolder.createSessionAndGetId(jwtUser, null);
        Thread.sleep(1000);
        assertThat(sessionsHolder.getSession(sessionId)).isEmpty();
    }
}