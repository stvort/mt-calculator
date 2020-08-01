package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Calculate.UsersResult")
public class UserResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private User user;

    @Embedded
    private EquationBrief equation;

    @Embedded
    private TrainingSessionBrief session;

    @Column(name = "Success")
    private Boolean success;

    @Column(name = "ResultDateStamp")
    private Date resultDate;
}
