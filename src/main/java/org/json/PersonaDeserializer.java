package org.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersonaDeserializer implements JsonDeserializer<Persona> {
    @Override
    public Persona deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jo = jsonElement.getAsJsonObject();
        String nom = jo.get("name").getAsString();
        String ed = jo.get("age").getAsString();
        String [] dir = jo.get("adress").getAsString().split(",");
        Direccion d = new Direccion(dir[0], dir[1]);
        List<Persona> listaP = new ArrayList<>();
        if (jo.get("amigos")!= null) {
            JsonArray ja = jo.getAsJsonArray("amigos");
            for (JsonElement j : ja) {
                Persona temp = jsonDeserializationContext.deserialize(j, Persona.class);
                listaP.add(temp);
            }
        } else {
            listaP = null;
        }

        String[] hobbies = null;
        if (jo.get("Hobbies")!=null) {
            hobbies = jo.get("Hobbies").getAsString().split("-");
        }
        Persona pers = new Persona(nom, ed, d);
        pers.setLista(listaP);
        pers.setHobbies(hobbies);

        return pers;
    }
}
