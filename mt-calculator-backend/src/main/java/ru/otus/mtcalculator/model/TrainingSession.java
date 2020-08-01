package ru.otus.mtcalculator.model;


import lombok.Data;
import ru.otus.mtcalculator.config.JwtUser;

import java.util.List;
import java.util.UUID;

@Data
public class TrainingSession {
    private String id;
    private JwtUser user;
    private long startTime ;
    private long stopTime;
    private List<Equation> equations;

    public TrainingSession(JwtUser user, List<Equation> equations) {
         id = UUID.randomUUID().toString();
         this.user = user;
         startTime = System.currentTimeMillis();
         stopTime = startTime;
         this.equations = equations;
    }

    public long getDurationSec() {
        return (stopTime - startTime) / 1000;
    }
}
