package org.json;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class PersonaSerializer implements JsonSerializer<Persona> {
    @Override
    public JsonElement serialize(Persona persona, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jo = new JsonObject();

        jo.addProperty("name", persona.getNombre());
        jo.addProperty("age", persona.getEdad());

        if (persona.getDireccion() != null) {
            jo.addProperty("adress", persona.getDireccion().calle + "," + persona.getDireccion().ciudad);
        } else {
            jo.add("adress", JsonNull.INSTANCE);
        }


        List<Persona> amigos = persona.getLista();
        if (amigos != null) {
            JsonArray ja = new JsonArray();
            for (Persona p : amigos) {
                ja.add(jsonSerializationContext.serialize(p));
            }
            jo.add("amigos", ja);
        } else {
            jo.add("amigos", JsonNull.INSTANCE);
        }


        String[] hobbies = persona.getHobbies();
        StringBuilder sb = new StringBuilder();
        if (hobbies != null) {
            for (String s : persona.getHobbies()) {
                sb.append(s).append("-");
            }
            sb.deleteCharAt(sb.lastIndexOf("-"));
            jo.addProperty("Hobbies", sb.toString());
        } else {
            jo.add("hobbies", JsonNull.INSTANCE);
        }

        return jo;
    }
}

