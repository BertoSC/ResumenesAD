package jsonGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Objects;

public class Game {
    public class jsonGame {
        int id;
        String titulo;
        Imagen miniatura;
        String descripcion;
        LocalDate fechaRealizacion;

        public jsonGame(){}

        public jsonGame(int id, String titulo, Imagen miniatura, String descripcion, LocalDate fechaRealizacion) {
            this.id = id;
            this.titulo = titulo;
            this.miniatura = miniatura;
            this.descripcion = descripcion;
            this.fechaRealizacion = fechaRealizacion;
        }

        public int getId() {
            return id;
        }

        public jsonGame setId(int id) {
            this.id = id;
            return this;
        }

        public String getTitulo() {
            return titulo;
        }

        public jsonGame setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Imagen getMiniatura() {
            return miniatura;
        }

        public jsonGame setMiniatura(Imagen miniatura) {
            this.miniatura = miniatura;
            return this;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public jsonGame setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public LocalDate getFechaRealizacion() {
            return fechaRealizacion;
        }

        public jsonGame setFechaRealizacion(LocalDate fechaRealizacion) {
            this.fechaRealizacion = fechaRealizacion;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            jsonGame jsonGame = (jsonGame) o;
            return id == jsonGame.id && Objects.equals(titulo, jsonGame.titulo) && Objects.equals(miniatura, jsonGame.miniatura) && Objects.equals(descripcion, jsonGame.descripcion) && Objects.equals(fechaRealizacion, jsonGame.fechaRealizacion);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, titulo, miniatura, descripcion, fechaRealizacion);
        }

        @Override
        public String toString() {
            return "jsonGame{" +
                    "id=" + id +
                    ", titulo='" + titulo + '\'' +
                    ", miniatura=" + miniatura +
                    ", descripcion='" + descripcion + '\'' +
                    ", fechaRealizacion=" + fechaRealizacion +
                    '}';
        }
    }

}
