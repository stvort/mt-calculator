package ru.otus.mtcalculator.resopsitories;

import ru.otus.mtcalculator.model.Equation;

import java.util.List;

public interface EquationsRepository {

    List<Equation> findRandomEquations(int equationsCount);
}
