package ru.otus.mtcalculator.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.Equation;
import ru.otus.mtcalculator.model.TrainingSession;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TrainingSessionsHolderImpl implements TrainingSessionsHolder {

    private final Cache<String, TrainingSession> trainingSessionCache;

    public TrainingSessionsHolderImpl(@Value("${app.session-life-time-sec}") int sessionLifeTimeSec) {
        trainingSessionCache = CacheBuilder.newBuilder()
                .expireAfterWrite(sessionLifeTimeSec, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public String createSessionAndGetId(JwtUser user, List<Equation> equations) {
        TrainingSession session = new TrainingSession(user, equations);
        trainingSessionCache.put(session.getId(), session);
        return session.getId();
    }

    @Override
    public Optional<TrainingSession> getSession(String id) {
        return Optional.ofNullable(trainingSessionCache.asMap().get(id));
    }

    @Override
    public Optional<TrainingSession> finishSession(String id) {
        return Optional.ofNullable(trainingSessionCache.asMap().get(id))
                .map(s -> {
                    s.setStopTime(System.currentTimeMillis());
                    return s;
                });
    }
}
