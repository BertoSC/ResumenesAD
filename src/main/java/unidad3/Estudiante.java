package unidad3;

import jakarta.persistence.*;

import java.time.LocalDate;

// Ejercicio creación de una ENTIDAD


@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstudiante", nullable = false)
    private Long idEstudiante;
    private String nombre;
    private String apellidos;
    private LocalDate fechaDeNacimiento;
    private String direccion;

    public Estudiante(String nombre, String apellidos, LocalDate fechaDeNacimiento, String direccion, Long idEstudiante) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(String nombre, String apellidos, LocalDate fechaDeNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Estudiante(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Estudiante() {
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante=" + idEstudiante +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
