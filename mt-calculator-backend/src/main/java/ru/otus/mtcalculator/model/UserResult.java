package ru.otus.mtcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@Table(name = "Calculate.UsersResult")
public class UserResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private UserBrief user;

    @Embedded
    private EquationBrief equation;

    @Embedded
    private TrainingSessionBrief session;

    @Column(name = "Success")
    private Boolean success;

    @Column(name = "ResultDateStamp")
    private Date resultDate;
}
