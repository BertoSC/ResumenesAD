package org.BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BookDAO implements DAO<Book>{
    Connection con;

    public BookDAO (Connection conex){
        this.con=conex;
    }


    @Override
    public Book get(long id) {
        String selecc = "select * from Book where idBook=id";
        try (PreparedStatement ps = con.prepareStatement(selecc)){
            ResultSet resultado= ps.getResultSet();
            Book temp = new Book();
            while (resultado.next()){
                Long identif = resultado.getLong("idBook");
                String is = resultado.getString("isbn");
                String tit = resultado.getString("titulo");
                String aut = resultado.getString("autor");
                int year = resultado.getInt("anho");
                boolean disp = resultado.getBoolean("disponible");
                byte [] port = resultado.getBytes("portada");
                Date date = resultado.getDate("dataPublicacion");
                temp.setIdBook(identif).setIsbn(is).setTitle(tit).setAuthor(aut).setYear(year).setAvaliable(disp).setPortada(port).setDataPublicacion(date);
            }
            return temp;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public List<Integer> getAllIds() {
        return List.of();
    }

    @Override
    public void updateLOB(Book book, String f) {

    }

    @Override
    public void updateLOBById(long id, String f) {

    }

    @Override
    public void deleteAll() {

    }
}
