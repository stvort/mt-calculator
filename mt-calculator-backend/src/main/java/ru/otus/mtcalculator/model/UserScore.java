package ru.otus.mtcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@Table(name = "Calculate.UsersScore")
public class UserScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private UserBrief user;

    @Column(name = "Time")
    private long duration;

    @Embedded
    private TrainingSessionBrief session;

    @Column(name = "Score")
    private long score;

    @Column(name = "ResultDateStamp")
    private Date resultDate;
}
