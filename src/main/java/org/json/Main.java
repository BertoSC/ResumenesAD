package org.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


        public class Main {
            public static void main(String[] args) {
                Persona p5 = new Persona("paco", "35", new Direccion("callejuela", "Santiago"));

                List<Persona> nombres = new ArrayList<>();
                nombres.add(new Persona("Luz Pozo", "45", new Direccion("callejuela", "Santiago")));
                nombres.add(new Persona("Rosalía", "5", new Direccion("avenida", "Porto")));
                nombres.add(new Persona("A tope", "25", new Direccion("oxight", "Xandar")));
                nombres.add(new Persona("Tú si que sabes", "65", new Direccion("Viva", "Mexico")));
                nombres.add(new Persona("Dalo todo", "8", new Direccion("A ver de esta", "Si va")));

                String[] hobbies = {"Nadar", "viciar", "fumar", "comer", "mandanga", "el delicioso"};
                p5.setLista(nombres);
                p5.setHobbies(hobbies);

                // Serializar el objeto p5
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(Persona.class, new PersonaSerializer())
                        .create();

                String prueba = gson.toJson(p5); // Serializa p5 directamente

                try (var flujo = Files.newBufferedWriter(Paths.get("personaJson.json"))) {
                    flujo.write(prueba);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }



