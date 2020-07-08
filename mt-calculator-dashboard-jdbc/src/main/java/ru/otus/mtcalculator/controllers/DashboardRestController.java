package ru.otus.mtcalculator.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.dto.CommonStatElemDto;
import ru.otus.mtcalculator.dto.UserStatElemDto;
import ru.otus.mtcalculator.resopsitories.UsersScoreRepository;
import ru.otus.mtcalculator.resopsitories.UsersScoreRepositoryMsSqlServer;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DashboardRestController {

    private final UsersScoreRepository repository;


    @GetMapping("api/v1/dashboard/self")
    public List<UserStatElemDto> getPersonalStat(Authentication a){
        JwtUser user = (JwtUser) a.getPrincipal();
        return repository.calcUsersStat(user.getId());
    }

    @GetMapping("api/v1/dashboard/all")
    public List<CommonStatElemDto> getCommonStat(){
        return repository.calcCommonStat();
    }



}
