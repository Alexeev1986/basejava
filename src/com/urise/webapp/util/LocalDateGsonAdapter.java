package com.urise.webapp.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateGsonAdapter extends TypeAdapter<LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.value(localDate.toString());
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        return LocalDate.parse(jsonReader.nextString());
    }
}
