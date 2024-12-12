package jsonGame;

public enum Plataforma {
    BROWSER("Web Browser"), PC("Pc (Windows)");
    String tipoPlataforma;

    Plataforma(String tipo){
        this.tipoPlataforma=tipo;
    }

    public String getTipoPlataforma() {
        return tipoPlataforma;
    }

    public Plataforma setTipoPlataforma(String tipoPlataforma) {
        this.tipoPlataforma = tipoPlataforma;
        return this;
    }

    public static Plataforma getTipoPlataforma (String tipoPlataforma){
        for (Plataforma plt:Plataforma.values()){
            if (plt.getTipoPlataforma().equals(tipoPlataforma)){
                return plt;
        }

    }
        return null;
    }

}
