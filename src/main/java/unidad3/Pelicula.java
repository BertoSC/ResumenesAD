package unidad3;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
//@Table(name = "peliculas")
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="id_pelicula", nullable = false)
    private Long idPelicula;
    private String titulo;
    private short ano;

    // relación entre entidades
    @ManyToOne
    @JoinColumn(name ="idDirector")
    Director director;

    public Pelicula(Long idPelicula, short ano, String titulo) {
        this.idPelicula = idPelicula;
        this.ano = ano;
        this.titulo = titulo;
    }


    public Pelicula(String titulo,short ano) {
        this.titulo = titulo;
        this.ano = ano;
    }

    public Pelicula() {
    }

    public Long getIdPelicula() {
        return idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public short getAno() {
        return ano;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "idPelicula=" + idPelicula +
                ", titulo='" + titulo + '\'' +
                ", ano='" + ano + '\'' +
                '}';
    }
}

