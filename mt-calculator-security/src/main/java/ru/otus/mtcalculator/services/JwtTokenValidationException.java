package ru.otus.mtcalculator.services;

public class JwtTokenValidationException extends RuntimeException {
    public JwtTokenValidationException(Throwable cause) {
        super(cause);
    }
}
