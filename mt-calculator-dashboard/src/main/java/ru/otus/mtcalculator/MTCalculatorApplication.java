package ru.otus.mtcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.mtcalculator.resopsitories.UsersScoreRepository;

/*
Задача:
На get запрос вернуть отвест со сводной статистикой
ручка dashboard/all
[{
    "Пользователь": "Сергей",
    "Счет последний": 43
    "Счет максимальный": 100
    "Счет средний за месяцу":80
}]

На get запрос вернуть ответ с сводной статистикой пользователя
Ручка dashboard/self
[{
    "Дата": "2020-03-02",
    "Длительность прохождения": 300,
    "Количество правильных ответов": 8,
    "Всего вопросов":8,
    "Счет": 100
}]

 */

@SpringBootApplication
public class MTCalculatorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(MTCalculatorApplication.class, args);
		UsersScoreRepository usersScoreRepository = ctx.getBean(UsersScoreRepository.class);
	}

}

/*
CREATE TABLE [Users].[Main](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Login] [nvarchar](100) NULL,
	[Password] [nvarchar](20) NULL,
	[BirthDay] [date] NULL,
	[NickName] [nvarchar](25) NULL
)

CREATE TABLE [Calculate].[UsersScore](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NOT NULL,
	[Time] [bigint] NOT NULL,
	[Session] [uniqueidentifier] NOT NULL,
	[Score] [bigint] NOT NULL,
	[ResultDateStamp] [datetime] NULL
)


CREATE TABLE [Calculate].[UsersResult](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NULL,
	[EquationId] [bigint] NULL,
	[Success] [bit] NULL,
	[Session] [uniqueidentifier] NULL,
	[ResultDateStamp] [datetime2](7) NULL
)

CREATE TABLE [Calculate].[Equations](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[FirstNumber] [bigint] NOT NULL,
	[SecondNumber] [bigint] NOT NULL,
	[Type] [nvarchar](10) NOT NULL
)
 */
