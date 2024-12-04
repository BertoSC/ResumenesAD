package org.BBDDfilosofos.modelo;

import java.util.Date;
import java.util.Objects;

public class Filosofo {
    int id;
    String nome;
    String apelidos;
    int idade;
    Date dataNacemento;

    public Filosofo(String nome, String apelidos, int idade, Date dataNacemento) {
        this.nome = nome;
        this.apelidos = apelidos;
        this.idade = idade;
        this.dataNacemento = dataNacemento;
    }

    public Filosofo(int id, String nome, String apelidos, int idade, Date dataNacemento) {
        this.id = id;
        this.nome = nome;
        this.apelidos = apelidos;
        this.idade = idade;
        this.dataNacemento = dataNacemento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(Date dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filosofo filosofo = (Filosofo) o;
        return id == filosofo.id && idade == filosofo.idade && Objects.equals(nome, filosofo.nome) && Objects.equals(apelidos, filosofo.apelidos) && Objects.equals(dataNacemento, filosofo.dataNacemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, apelidos, idade, dataNacemento);
    }

    @Override
    public String toString() {
        return "Filosofo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", apelidos='" + apelidos + '\'' +
                ", idade=" + idade +
                ", dataNacemento=" + dataNacemento +
                '}';
    }
}
