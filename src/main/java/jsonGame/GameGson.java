package jsonGame;

import com.google.gson.*;

import java.lang.reflect.Type;

public class GameGson implements JsonSerializer<Game>, JsonDeserializer<Game> {
    @Override
    public Game deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonArray ja = new JsonArray();
        for (JsonElement je: ja){
            JsonObject jo = new JsonObject();

        }

        return null;
    }

    @Override
    public JsonElement serialize(Game game, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
