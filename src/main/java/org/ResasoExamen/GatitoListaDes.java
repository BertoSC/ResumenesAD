package org.ResasoExamen;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GatitoListaDes implements JsonDeserializer<List<Gatito>> {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public List<Gatito> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray ja = jsonElement.getAsJsonArray();
        List<Gatito> lista = new ArrayList<>();
        for (JsonElement je: ja ){
            JsonObject jo = je.getAsJsonObject();
            Gatito temp = null;
            int id = jo.get("identificador").getAsInt();
            String nom = jo.get("nombre").getAsString();
            String raza = jo.get("razaFelina").getAsString();
            String url = jo.get("path").getAsString();
            LocalDate fecha = LocalDate.parse(jo.get("nacimiento").getAsString(), formato);
            temp = new Gatito(id, nom, raza, url, fecha);
            lista.add(temp);

        }

        return lista;
    }
}
