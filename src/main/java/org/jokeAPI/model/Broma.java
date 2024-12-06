package org.jokeAPI.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Broma implements Serializable {
    Boolean error;
    Categoria category;
    String type;
    String joke;
    List<TipoFlag> flagList;
    Boolean safe;
    int id;
    String lang;

    public Broma() {
    }

    public Broma(Boolean error, Categoria category, String type, String joke, List<TipoFlag> flagList, Boolean safe, int id, String lang) {
        this.error = error;
        this.category = category;
        this.type = type;
        this.joke = joke;
        this.flagList = flagList;
        this.safe = safe;
        this.id = id;
        this.lang = lang;
    }

    public Boolean getError() {
        return error;
    }

    public Broma setError(Boolean error) {
        this.error = error;
        return this;
    }

    public Categoria getCategory() {
        return category;
    }

    public Broma setCategory(Categoria category) {
        this.category = category;
        return this;
    }

    public String getType() {
        return type;
    }

    public Broma setType(String type) {
        this.type = type;
        return this;
    }

    public String getJoke() {
        return joke;
    }

    public Broma setJoke(String joke) {
        this.joke = joke;
        return this;
    }

    public List<TipoFlag> getFlagList() {
        return flagList;
    }

    public Broma setFlagList(List<TipoFlag> flagList) {
        this.flagList = flagList;
        return this;
    }

    public Boolean getSafe() {
        return safe;
    }

    public Broma setSafe(Boolean safe) {
        this.safe = safe;
        return this;
    }

    public int getId() {
        return id;
    }

    public Broma setId(int id) {
        this.id = id;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public Broma setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public void añadirFlag(TipoFlag flag){
        flagList.add(flag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Broma broma = (Broma) o;
        return id == broma.id && Objects.equals(error, broma.error) && category == broma.category && Objects.equals(type, broma.type) && Objects.equals(joke, broma.joke) && Objects.equals(flagList, broma.flagList) && Objects.equals(safe, broma.safe) && Objects.equals(lang, broma.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, category, type, joke, flagList, safe, id, lang);
    }

    @Override
    public String toString() {
        String categoria = "";
        if (category==null){
            categoria = "No especificada";
        } else {
            categoria = category.getTipoCategoria();
        }

        if (type.equals("single")){
            return "Categoria :"+categoria+System.lineSeparator()+System.lineSeparator()+
                    joke+System.lineSeparator();

        } else {
            String [] partes= joke.split("separaaqui");
            return "Categoria: " + categoria + System.lineSeparator() + System.lineSeparator()+
                    partes[0] + System.lineSeparator() +
                    partes[1] + System.lineSeparator();
        }
    }
}


