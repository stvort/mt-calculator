package ru.otus.mtcalculator.resopsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.mtcalculator.dto.CommonStatElemDto;
import ru.otus.mtcalculator.dto.UserStatElemDto;
import ru.otus.mtcalculator.model.UsersScore;

import java.util.List;

public interface UsersScoreRepository extends JpaRepository<UsersScore, Long> {
    @Query("select new ru.otus.mtcalculator.dto.CommonStatElemDto(" +
                "s.user.nickName, " +
                "(select max(s2.score) from UsersScore s2 where s2.resultTime = (select max(s3.resultTime) from UsersScore s3)), " +
                "max(s.score), " +
                "(" +
                    "select avg(cast(s4.score as double)) " +
                    "from UsersScore s4 " +
                    "where year(current_date()) = year(s4.resultTime) and month(current_date()) = month(s4.resultTime)" +
                ") " +
            ") " +
            "from UsersScore s " +
            "group by s.user.nickName")
    List<CommonStatElemDto> calcCommonStat();

    @Query("select new ru.otus.mtcalculator.dto.UserStatElemDto(" +
                "s.resultTime, " +
                "s.duration, " +
                "(select count(u2.equation.id) from UsersResult u2 where u2.session = s.session and u2.success <> true), " +
                "(select count(u1.equation.id) from UsersResult u1 where u1.session = s.session), " +
                "s.score " +
            ") " +
            "from UsersScore s " +
            "where s.user.id = :userId")
    List<UserStatElemDto> calcUsersStat(long userId);
}
