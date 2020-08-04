package ru.otus.mtcalculator.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.CommonStatElem;
import ru.otus.mtcalculator.model.UserStatElem;
import ru.otus.mtcalculator.resopsitories.UsersStatRepository;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class DashboardRestController {

    private final UsersStatRepository repository;


    @GetMapping("api/v1/dashboard/self")
    public List<UserStatElem> getPersonalStat(Authentication a){
        JwtUser user = (JwtUser) a.getPrincipal();
        return repository.calcUsersStat(user.getId());
    }

    @GetMapping("api/v1/dashboard/all")
    public List<CommonStatElem> getCommonStat(){
        long t = System.currentTimeMillis();
        List<CommonStatElem> commonStat = repository.calcCommonStat();
        System.out.printf("Elapsed time: %d\n", System.currentTimeMillis() - t);
        return commonStat;
    }



}
