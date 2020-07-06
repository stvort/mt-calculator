package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "Calculate", name = "UsersResult")
public class UsersResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private AppUser user;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "EquationId"))
    private Equation equation;

    @Column(name = "Success")
    private boolean success;

    @Column(name = "Session")
    private String session;
}
