package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
//@Entity
@Table(schema = "Calculate", name = "Sessions")
public class Session {

    @Id
    private UUID id;

    @Column
    private Date startTime;
}
