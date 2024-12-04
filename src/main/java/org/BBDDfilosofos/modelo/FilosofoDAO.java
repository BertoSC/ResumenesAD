package org.BBDDfilosofos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FilosofoDAO implements DAO<Filosofo>{

    Connection con;

    public FilosofoDAO(Connection con){
        this.con=con;
    }


    @Override
    public Filosofo get(int id) {

        return null;
    }

    @Override
    public List<Filosofo> getAll() {
        return null;
    }

    @Override
    public ResultSet getCursor() throws SQLException {
        String consulta = "select * from Filosofo";
        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(consulta);
        return rs;
    }

    @Override
    public List<Integer> getAllIds() {
        String consulta ="select idFilosofo from Filosofo";

        return null;
    }
}
