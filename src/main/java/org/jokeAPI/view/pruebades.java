package org.jokeAPI.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jokeAPI.model.Broma;
import org.jokeAPI.networkAndData.BromaDeserializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class pruebades {

    public static void main(String[] args) throws IOException {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Broma.class, new BromaDeserializer()).create();

        String json = Files.readString(Paths.get("C:/Users/a23albertogc/Desktop/AD/ResumenesAD/src/main/java/org/jokeAPI/view/broma.json"));

        Broma broma = gson.fromJson(json, Broma.class);

        System.out.println(broma);

    }
}
