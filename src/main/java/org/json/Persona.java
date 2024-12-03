package org.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Persona {

        String nombre;
        String edad;
        Direccion direccion;
        List<Persona> amigos= new ArrayList<>();
        String[] hobbies;

        public Persona(String n, String e, Direccion d) {
            this.nombre = n;
            this.edad = e;
            this.direccion = d;

        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEdad() {
            return edad;
        }

        public void setEdad(String edad) {
            this.edad = edad;
        }

        public Direccion getDireccion() {
            return direccion;
        }

        public void setDireccion(Direccion direccion) {
            this.direccion = direccion;
        }

        public List<Persona> getLista() {
            return amigos;
        }

        public void setLista(List<Persona> lista) {
            this.amigos = lista;
        }

        public String[] getHobbies() {
            return hobbies;
        }

        public void setHobbies(String[] hobbies) {
            this.hobbies = hobbies;
        }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                ", direccion=" + direccion +
                ", amigos=" + amigos +
                ", hobbies=" + Arrays.toString(hobbies) +
                '}';
    }
}
