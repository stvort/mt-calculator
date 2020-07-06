package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "Users", name = "Main")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NickName")
    private String nickName;


}
