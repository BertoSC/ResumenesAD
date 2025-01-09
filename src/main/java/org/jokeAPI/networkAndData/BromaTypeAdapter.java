package org.jokeAPI.networkAndData;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.jokeAPI.model.Broma;

import java.io.IOException;

public class BromaTypeAdapter extends TypeAdapter<Broma> {
    @Override
    public void write(JsonWriter jsonWriter, Broma broma) throws IOException {

    }

    @Override
    public Broma read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
