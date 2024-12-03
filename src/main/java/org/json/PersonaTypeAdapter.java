package org.json;

import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonaTypeAdapter extends TypeAdapter<Persona> {
    @Override
    public void write(JsonWriter jsonWriter, Persona persona) throws IOException {
        if (persona==null){
            jsonWriter.nullValue();
            return;
        }
       jsonWriter.beginObject();
       jsonWriter.name("nome").value(persona.getNombre());
       jsonWriter.name("idade").value(persona.getEdad());
       if (persona.getDireccion()!=null){
           jsonWriter.name("direccion");
           jsonWriter.beginObject();
           jsonWriter.name("rúa").value(persona.getDireccion().calle);
           jsonWriter.name("cidade").value(persona.getDireccion().ciudad);
           jsonWriter.endObject();
       }
       if(persona.getLista()!=null){
           jsonWriter.name("amigos");
           jsonWriter.beginArray();
           for (Persona p: persona.getLista()){
               write(jsonWriter, p);
           }
           jsonWriter.endArray();
       }else{
           jsonWriter.name("amigos").nullValue();
       }

       if (persona.getHobbies()!=null){
           StringBuilder sb = new StringBuilder();
           for (String s: persona.getHobbies()){
               sb.append(s).append("-");
           }
           jsonWriter.name("aficions").value(sb.deleteCharAt(sb.lastIndexOf("-")).toString());
       } else {
           jsonWriter.name("aficions").nullValue();
       }

       jsonWriter.endObject();

    }

    @Override
    public Persona read(JsonReader jsonReader) throws IOException {
        String nombre= null;
        String edad = null;
        Direccion direc = null;
        List<Persona> amigos = new ArrayList<>();
        String [] aficiones = null;
        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            String atributo= jsonReader.nextName();
            switch (atributo){
                case "nome":
                    nombre=jsonReader.nextString();
                    break;
                case "idade":
                    edad=jsonReader.nextString();
                    break;
                case "direccion":
                    direc= direccionReader(jsonReader);
                    break;
                case "amigos":
                    JsonToken amig = jsonReader.peek();
                    if (amig!=JsonToken.NULL) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            amigos.add(read(jsonReader));
                        }
                        jsonReader.endArray();
                    } else {
                        amigos = new ArrayList<>();
                    }

                    break;
                case "aficions":
                    aficiones=jsonReader.nextString().split("-");
                    break;

            }
        }
        jsonReader.endObject();
        Persona temp = new Persona(nombre, edad, direc);
        temp.setLista(amigos);
        temp.setHobbies(aficiones);
        return temp;
    }

    private Direccion direccionReader(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        Direccion d = null;
        String calle= null;
        String ciudad=null;
        while (jsonReader.hasNext()){
            String atrib = jsonReader.nextName();
            switch (atrib){
                case "rúa":
                    calle= jsonReader.nextString();
                    break;
                case "cidade":
                    ciudad=jsonReader.nextString();
                    break;

            }
        }
        jsonReader.endObject();
        return d = new Direccion(calle, ciudad);

    }
}
