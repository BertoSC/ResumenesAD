package unidad3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Director implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long idDirector;
    String nome;
    String apelidos;
    String nacionalidad;

    public Director(String nome, Long idDirector, String apelidos, String nacionalidad) {
        this.nome = nome;
        this.idDirector = idDirector;
        this.apelidos = apelidos;
        this.nacionalidad = nacionalidad;
    }

    public Director(String nome, String apelidos) {
        this.nome = nome;
        this.apelidos = apelidos;
    }

    public Director() {
    }

    public Long getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Long idDirector) {
        this.idDirector = idDirector;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Director{" +
                "idDirector=" + idDirector +
                ", nome='" + nome + '\'' +
                ", apelidos='" + apelidos + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
