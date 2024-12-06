package org.jokeAPI.model;

public enum Categoria {
    ANY("any"), PROGRAMMING("Programming"), MISCELLANEUS("Miscellaneous"), DARK("Dark"), PUN("Pun"), SPOOKY("Spooky"), CHRISTMAS("Christmas");

    private String tipoCategoria;

    Categoria(String s){
        this.tipoCategoria=s;

    }

    public String getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipo){
        this.tipoCategoria=tipo;
    }

    public static Categoria getTipoCategoria (String tipo){
        for (Categoria s: Categoria.values()){
            if (tipo.equals(s.getTipoCategoria())){
                return s;
            }
        }
        return null;
    }

}
