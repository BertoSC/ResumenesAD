package org.ResasoExamen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Gatito {
    int id;
    String nombre;
    String raza;
    byte[] foto;
    String urlFoto;
    LocalDate fecha;

    public Gatito() {
    }

    public Gatito(String nombre, String raza, String urlFoto, LocalDate fecha) {
        this.nombre = nombre;
        this.raza = raza;
        this.urlFoto = urlFoto;
        this.fecha = fecha;
    }

    public Gatito(String nombre, String raza, byte[] foto, String urlFoto) {
        this.nombre = nombre;
        this.raza = raza;
        this.foto = foto;
        this.urlFoto = urlFoto;
    }

    public Gatito(int id, String nombre, String raza, byte[] foto, String urlFoto, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.foto = foto;
        this.urlFoto = urlFoto;
        this.fecha=fecha;
    }

    public Gatito(int id, String nombre, String raza, String urlFoto, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.foto = foto;
        this.urlFoto = urlFoto;
        this.fecha=fecha;
    }

    public int getId() {
        return id;
    }

    public Gatito setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Gatito setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getRaza() {
        return raza;
    }

    public Gatito setRaza(String raza) {
        this.raza = raza;
        return this;
    }

    public byte[] getFoto() {
        return foto;
    }

    public Gatito setFoto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public Gatito setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
        return this;
    }

    public byte[] conseguirArrayBites(String url) throws IOException {
        File ruta = new File(url);
        BufferedImage img = ImageIO.read(ruta);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img,"png", out);
        this.foto= out.toByteArray();
        return this.foto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Gatito setFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gatito gatito = (Gatito) o;
        return id == gatito.id && Objects.equals(nombre, gatito.nombre) && Objects.equals(raza, gatito.raza) && Arrays.equals(foto, gatito.foto) && Objects.equals(urlFoto, gatito.urlFoto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nombre, raza, urlFoto);
        result = 31 * result + Arrays.hashCode(foto);
        return result;
    }

    @Override
    public String toString() {
        return "Este gatito se llama "+ nombre + ", es de raza "+raza+", nació el "+fecha+" y su identificador es "+id
                +System.lineSeparator()+"Su foto está en esta ruta del sistema de archivos: "+urlFoto;
    }
}

