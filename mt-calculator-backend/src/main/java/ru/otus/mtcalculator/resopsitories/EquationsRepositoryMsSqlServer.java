package ru.otus.mtcalculator.resopsitories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.otus.mtcalculator.model.Equation;

import java.util.List;

@Mapper
public interface EquationsRepositoryMsSqlServer extends EquationsRepository {

    @Override
    @Select("select top(#{equationsCount}) Id id, FirstNumber firstNumber, SecondNumber secondNumber, Type type " +
            "from [Calculate].[Equations] " +
            "where (Type='m' and FirstNumber <= 10 and SecondNumber <= 10) or \n" +
            "      (Type='d' and FirstNumber % SecondNumber = 0 and SecondNumber <= 10 and FirstNumber / SecondNumber <= 10) " +
            "order by NEWID()")
    List<Equation> findRandomEquations(int equationsCount);
}
