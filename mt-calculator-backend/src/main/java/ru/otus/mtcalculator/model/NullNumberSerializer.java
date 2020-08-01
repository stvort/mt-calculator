package ru.otus.mtcalculator.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NullNumberSerializer extends JsonSerializer<Number> {
    @Override
    public void serialize(Number val, JsonGenerator g, SerializerProvider p) throws IOException {
        Number n = val == null? 0: val;
        g.writeString(n.toString());
    }
}
