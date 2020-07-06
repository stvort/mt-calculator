package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "Calculate", name = "UsersScore")
public class UsersScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private AppUser user;

    @Column(name = "Time")
    private int duration;

    @Column(name = "Score")
    private int score;

    @Column(name = "ResultDateStamp")
    private Date resultTime;

    @Column(name = "Session")
    private String session;

}
