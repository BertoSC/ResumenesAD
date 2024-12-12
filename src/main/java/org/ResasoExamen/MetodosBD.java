package org.ResasoExamen;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MetodosBD {
    Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\AD\\Gatito");
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public MetodosBD() throws SQLException {}

    public boolean save (Gatito gato) throws SQLException {
        String consulta = "insert into Gatito (nombre, raza, foto, urlfoto, fecha) values (?,?,?,?,?)";

        try(PreparedStatement ps = con.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, gato.getNombre());
            ps.setString(2, gato.getRaza());
            ps.setBytes(3, gato.getFoto());
            ps.setString(4, gato.getUrlFoto());
            ps.setString(5, gato.getFecha().toString());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
           while(keys.next()){
               int id = keys.getInt(1);
               gato.setId(id);
            }
            System.out.println("OPERACION REALIZADA");
            return true;
        }

    }

    public List<Gatito> getAll() throws SQLException {
        String consulta = "select * from Gatito";
        try (PreparedStatement ps = con.prepareStatement(consulta)){
            ResultSet rs = ps.executeQuery();
            List <Gatito> listaFelina = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString("nombre");
                String raza = rs.getString("raza");
                byte [] foto = rs.getBytes("foto");
                String url = rs.getString("urlfoto");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"),formato);
                Gatito temp =new Gatito(id, nom, raza, foto, url, fecha);
                listaFelina.add(temp);
            }
            return listaFelina;

        }
    }
}
