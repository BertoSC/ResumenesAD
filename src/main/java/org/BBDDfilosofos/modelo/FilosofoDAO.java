package org.BBDDfilosofos.modelo;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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

    public void update(ResultSet rs, String nom, String ape, int ed, String fecha, Boolean select) throws SQLException {
        if (select) {
            rs.updateString("nome", nom);
            rs.updateString("apelidos", ape);
            rs.updateInt("idade", ed);
            rs.updateDate("dataNacemento", java.sql.Date.valueOf(fecha));
            rs.updateRow();
        } else {
            rs.moveToInsertRow();
            rs.updateString("nome", nom);
            rs.updateString("apelidos", ape);
            rs.updateInt("idade", ed);
            rs.updateDate("dataNacemento", java.sql.Date.valueOf(fecha));
            rs.insertRow();


        }
    }

    public Boolean deleteByID(int id){
        String orden = "delete * from Filosofo where idFilosofo =?";

        return false;
    }

    @Override
    public ResultSet getCursor() throws SQLException {
        String consulta = "select * from Filosofo order by idFilosofo";
        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(consulta);

        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }


    @Override
    public List<Integer> getAllIds() {
        String consulta ="select idFilosofo from Filosofo";

        return null;
    }
}
