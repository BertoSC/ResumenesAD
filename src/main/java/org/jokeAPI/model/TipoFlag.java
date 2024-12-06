package org.jokeAPI.model;

public enum TipoFlag {
    NSFW("nsfw"), RELIGIOUS("religious"), POLITICAL("political"), RACIST ("racist"), SEXIST("sexist"), EXPLICIT("explicit");

    String tipoFlag;

    TipoFlag(String tipo){
        this.tipoFlag=tipo;
    }

    public String getTipoFlag() {
        return tipoFlag;
    }

    public void setTipoFlag(String tipoFlag) {
        this.tipoFlag = tipoFlag;
    }

    public static TipoFlag getTipoFlag (String tipo) {
        for (TipoFlag tip: TipoFlag.values()){
            if (tip.getTipoFlag().equals(tipo)){
                return tip;
            }
        }
        return null;
    }

}
