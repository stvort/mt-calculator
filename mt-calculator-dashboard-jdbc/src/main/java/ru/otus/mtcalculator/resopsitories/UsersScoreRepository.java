package ru.otus.mtcalculator.resopsitories;

import ru.otus.mtcalculator.dto.CommonStatElemDto;
import ru.otus.mtcalculator.dto.UserStatElemDto;

import java.util.List;

public interface UsersScoreRepository {
    List<CommonStatElemDto> calcCommonStat();
    List<UserStatElemDto> calcUsersStat(long userId);
}
