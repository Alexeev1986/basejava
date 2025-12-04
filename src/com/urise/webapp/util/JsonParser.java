package com.urise.webapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.urise.webapp.model.AbstractSection;

import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

public class JsonParser {
    private final static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter())
            .registerTypeAdapter(AbstractSection.class, new JsonSectionAdapter<AbstractSection>())
            .setPrettyPrinting()
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return gson.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        gson.toJson(object, writer);
    }
}
