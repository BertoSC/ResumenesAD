package org.jokeAPI.networkAndData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jokeAPI.model.Broma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class BromaDAO implements DAO<String> {
    private final static String BASE_URL = "https://v2.jokeapi.dev/joke/";
    Gson gson;

    public BromaDAO() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Broma.class, new BromaDeserializer()).create();

    }

    @Override
    public Broma getChiste() {
        String urlConsulta=BASE_URL+"Any?lang=es";
        StringBuilder json = new StringBuilder();
        try {
            URI uri = new URI(urlConsulta);
            HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode()==HttpURLConnection.HTTP_OK){
                try (var in = new BufferedReader(new InputStreamReader(con.getInputStream()))){
                    String line;
                    while ((line=in.readLine())!=null){
                        json.append(line);
                    }
                }
                String jsonJoke = json.toString();
                Broma joke = gson.fromJson(jsonJoke, Broma.class);
                return joke;
            } else {
                return null;
           }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Broma getChisteByLang(String lang) {
        String url = BASE_URL+"Any?lang="+lang;
        StringBuilder json = new StringBuilder();
        try {
            URI uri = new URI(url);
            HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode()== HttpURLConnection.HTTP_OK){
                try (var in = new BufferedReader(new InputStreamReader(con.getInputStream()))){
                    String line;
                    while ((line=in.readLine())!=null) {
                        json.append(line);
                    }
                }
                Broma joke = gson.fromJson(json.toString(), Broma.class);
                return joke;
            } else {
                return null;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Broma getChisteByCategory(String category) {
        String url = BASE_URL+category+"?/lang=es";
        StringBuilder joke = new StringBuilder();
        try {
            URI uri = new URI(url);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                String line;
                try (var in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                    while((line=in.readLine())!=null){
                        joke.append(line);
                    }
                }
                Broma broma = gson.fromJson(joke.toString(), Broma.class);
                return broma;
            } else {
                return null;
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
