package ru.otus.mtcalculator.resopsitories;

import org.springframework.cache.annotation.Cacheable;
import ru.otus.mtcalculator.dto.CommonStatElemDto;
import ru.otus.mtcalculator.dto.UserStatElemDto;

import java.util.List;

public interface UsersScoreRepository {
    @Cacheable(value = "commonStat")
    List<CommonStatElemDto> calcCommonStat();

    @Cacheable(value = "userStat", key = "#userId")
    List<UserStatElemDto> calcUsersStat(long userId);
}
