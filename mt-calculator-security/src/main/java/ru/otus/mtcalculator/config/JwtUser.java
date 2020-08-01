package ru.otus.mtcalculator.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class JwtUser extends User {
    @Getter
    private long id;
    @Getter
    private LocalDate birthDate;

    public JwtUser(long id, String username, LocalDate birthDate, Collection<? extends GrantedAuthority> authorities) {
        super(username, "", authorities);
        this.id = id;
        this.birthDate = birthDate;
    }
}
