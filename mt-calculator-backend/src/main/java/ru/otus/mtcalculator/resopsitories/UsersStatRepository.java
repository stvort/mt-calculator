package ru.otus.mtcalculator.resopsitories;

import org.springframework.cache.annotation.Cacheable;
import ru.otus.mtcalculator.model.CommonStatElem;
import ru.otus.mtcalculator.model.UserStatElem;

import java.util.List;

public interface UsersStatRepository {
    @Cacheable(value = "commonStat")
    List<CommonStatElem> calcCommonStat();

    @Cacheable(value = "userStat", key = "#userId")
    List<UserStatElem> calcUsersStat(long userId);
}
