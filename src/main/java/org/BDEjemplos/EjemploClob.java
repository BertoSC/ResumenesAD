package org.BDEjemplos;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.*;

public class EjemploClob {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:h2:C:\\Users\\a23albertogc\\Desktop\\AD\\H2", "", "");
            Clob clobEjemplo = con.createClob();
            String textoGrande = "Este es un texto muy grande que flipas colega, neno, a ver si se guarda eh...";

            try(PreparedStatement ps = con.prepareStatement("INSERT INTO \"Animal\"(\"nome\", \"descripcion\") VALUES (?, ?)")){

                ps.setString(1, "ejemplo");
                clobEjemplo.setString(1, textoGrande);
                //ps.setClob(2, clobEjemplo);
                Reader reader = new StringReader(textoGrande);
                ps.setCharacterStream(2, reader, textoGrande.length());
                ps.executeUpdate();

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
