package ru.otus.mtcalculator.resopsitories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.otus.mtcalculator.dto.CommonStatElemDto;
import ru.otus.mtcalculator.dto.UserStatElemDto;

import java.util.List;

@Mapper
public interface UsersScoreRepository {

    @Select("with MaxResultTimeCte as (" +
                "select UserId, " +
                    "max(ResultDateStamp) MaxResultTime,  " +
                    "max(score) maxScore, " +
                    "avg(CASE WHEN (year(ResultDateStamp) = year(CURRENT_TIMESTAMP) and month(ResultDateStamp) = month(CURRENT_TIMESTAMP)) " +
                        "THEN (score + 0.0) ELSE null END) avgScore " +
                "from Calculate.UsersScore " +
                "group by UserId " +
            "), MaxResultCte as ( " +
                "select s.UserId, score lastScore, t.maxScore, avgScore " +
                "from Calculate.UsersScore s inner join MaxResultTimeCte t on s.UserId = t.UserId and s.ResultDateStamp = t.MaxResultTime " +
            ") " +
            "select NickName userName, lastScore, maxScore, avgScore " +
            "from MaxResultCte r left join Users.Main u on r.UserId = u.Id " +
            "order by avgScore desc"
    )
    List<CommonStatElemDto> calcCommonStat();


    @Select("select s.ResultDateStamp date, s.Time duration, s.score score, " +
            "sum(CASE WHEN (r.success = 0 or r.Success is null) THEN 0 ELSE 1 END) rightAnswersCount, " +
            "count(r.EquationId) totalQuestionCount " +
            "from Calculate.UsersScore s left join Calculate.UsersResult r on s.session = r.session " +
            "where s.UserId = #{userId} " +
            "group by s.[ResultDateStamp], s.[Time], s.score")
    List<UserStatElemDto> calcUsersStat(long userId);
}
