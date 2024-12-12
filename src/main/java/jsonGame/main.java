package jsonGame;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Type tipo = new TypeToken <List<Game>>(){}.getType();
    }
}
