package org.ResasoExamen;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

public class GatitoGson implements JsonSerializer<Gatito>, JsonDeserializer<Gatito> {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public Gatito deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(Gatito gatito, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jo = new JsonObject();
        jo.addProperty("identificador", gatito.getId());
        jo.addProperty("nombre", gatito.getNombre());
        jo.addProperty("razaFelina", gatito.getRaza());
        jo.addProperty("path", gatito.getUrlFoto());
        jo.addProperty("nacimiento", gatito.getFecha().format(formato));
        return jo;
    }
}
