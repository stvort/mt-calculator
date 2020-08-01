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
            "where FirstNumber<10 and SecondNumber<10 and (Type='m' or FirstNumber%SecondNumber=0) " +
            "order by NEWID()")
    List<Equation> findRandomEquations(int equationsCount);
}
