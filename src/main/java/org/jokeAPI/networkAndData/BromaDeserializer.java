package org.jokeAPI.networkAndData;

import com.google.gson.*;
import org.jokeAPI.model.Broma;
import org.jokeAPI.model.Categoria;
import org.jokeAPI.model.TipoFlag;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BromaDeserializer implements JsonDeserializer<Broma> {
    @Override
    public Broma deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Boolean error= null;
        Categoria category = null;
        String tipo = null;
        String joke = null;
        List<TipoFlag> flagList = null;
        Boolean safe = null;
        int id = 0;
        String lang = null;
        Broma broma = null;

        if (!jsonElement.isJsonObject()){
            return null;
        }


        JsonObject jo = jsonElement.getAsJsonObject();

        // PEPE HACE COMPROBACIONES Y NO METIÓ EL ATRIBUTO ERROR, LO USA PARA COMPROBAR SI ES TRUE O NO


        error = jo.get("error").getAsBoolean();

        // comprobación de nulos  // extramos el campo como elemneto, para verificar si existe y, además, que no sea null antes de extraerlo
        // eb ese caso, decidimos si asignar la variable a null

        JsonElement categoryElement = jo.get("category");
        if (categoryElement != null && !categoryElement.isJsonNull()) {
            category = Categoria.getTipoCategoria(jo.get("category").getAsString());
        } else {
            category = null;
        }

        tipo = jo.get("type").getAsString();

        if (tipo.equals("single")){
            joke=jo.get("joke").getAsString();
        } else {
            String setup=jo.get("setup").getAsString();
            String delivery= jo.get("delivery").getAsString();
            joke= setup+"separaaqui"+delivery;
        }
/*
        JsonObject banderas = jo.get("flags").getAsJsonObject();
        flagList = deserializarListaFlags(banderas);
  */

        // Campo "flags"

        if (jo.has("flags") && !jo.get("flags").isJsonNull()) {
            JsonObject banderas = jo.get("flags").getAsJsonObject();
            flagList = deserializarListaFlags(banderas);
        } else {
            flagList = new ArrayList<>();
        }



        safe = jo.get("safe").getAsBoolean();
        id = jo.get("id").getAsInt();
        lang = jo.get("lang").getAsString();
        broma = new Broma(error, category, tipo, joke, flagList, safe, id, lang);
        return broma;
    }

    private List<TipoFlag> deserializarListaFlags(JsonObject banderas) {
        List<TipoFlag> listaFlags = new ArrayList<>();

        for (TipoFlag tipoFlag : TipoFlag.values()) {
            JsonElement element = banderas.get(tipoFlag.getTipoFlag());

            if (element != null && !element.isJsonNull()) {
                boolean temp = element.getAsBoolean();
                if (temp) {
                    listaFlags.add(tipoFlag);
                }
            }
        }

        return listaFlags;
    }
}
