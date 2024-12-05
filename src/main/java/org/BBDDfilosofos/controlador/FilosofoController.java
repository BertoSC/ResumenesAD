package org.BBDDfilosofos.controlador;

import org.BBDD.Book;
import org.BBDDfilosofos.modelo.FilosofoDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FilosofoController {
    private ResultSet rs;
    private FilosofoDAO filDao;
    private int index = -1;

    private int id;
    private String nome;
    private String apelidos;
    private int idade;
    private java.sql.Date dataNacemento;

    public FilosofoController(Connection con) throws SQLException {
        filDao = new FilosofoDAO(con);
        rs = filDao.getCursor();
    }

    public Boolean previo() throws SQLException {
        if (rs.previous()) {
            index--;
            return true;
        }
        return false;
    }

    public Boolean siguiente() throws SQLException {
        if (rs.next()) {
            index++;
            return true;
        }
        return false;
    }

    public void setDatosActuales() throws SQLException {
        if (rs != null) {
            id = rs.getInt("idFilosofo");
            nome = rs.getString("nome");
            apelidos = rs.getString("apelidos");
            idade = rs.getInt("idade");
            dataNacemento = rs.getDate("dataNacemento");
        }
    }

    public void actualizarDatos(String nom, String ape, int ed, String fecha, Boolean select) throws SQLException {
        filDao.update(rs, nom, ape, ed, fecha, select);
    }



    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public int getIdade() {
        return idade;
    }

    public java.sql.Date getDataNacemento() {
        return dataNacemento;
    }
}