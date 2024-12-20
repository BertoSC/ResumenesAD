package org.ResasoExamen;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class LocalDateGson implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE;
    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDate.parse(jsonElement.getAsString(), formato);
    }

       @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDate.format(formato));
    }
}
