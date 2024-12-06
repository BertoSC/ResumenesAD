package org.jokeAPI.networkAndData;

import org.jokeAPI.model.Broma;

public interface DAO <String>{

    public Broma getChiste();
    public Broma getChisteByLang(String Lang);
    public Broma getChisteByCategory(String category);


}
