package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Calculate.UsersScore")
public class UserScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private User user;

    @Column(name = "Time")
    private long duration;

    @Embedded
    private TrainingSessionBrief session;

    @Column(name = "Score")
    private long score;

    @Column(name = "ResultDateStamp")
    private Date resultDate;
}
