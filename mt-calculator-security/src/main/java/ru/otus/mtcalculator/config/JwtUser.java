package ru.otus.mtcalculator.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUser extends User {
    @Getter
    private long id;

    public JwtUser(long id, String username, Collection<? extends GrantedAuthority> authorities) {
        super(username, "", authorities);
        this.id = id;
    }
}
