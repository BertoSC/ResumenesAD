package org.BBDD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDAO implements DAO<Book> {
    Connection con;

    public BookDAO(Connection conex) {
        this.con = conex;
    }


    @Override
    public Book get(int id) {
        String selecc = "select * from Book where idBook=?";
        try (PreparedStatement ps = con.prepareStatement(selecc)) {
            ps.setLong(1, id);
            ResultSet resultado = ps.executeQuery(selecc);
            Book temp = new Book();
            while (resultado.next()) {
                Integer identif = resultado.getInt("idBook");
                String is = resultado.getString("isbn");
                String tit = resultado.getString("titulo");
                String aut = resultado.getString("autor");
                int year = resultado.getInt("anho");
                boolean disp = resultado.getBoolean("disponible");
                byte[] port = resultado.getBytes("portada");
                LocalDate date = resultado.getDate("dataPublicacion").toLocalDate();
                temp.setIdBook(identif).setIsbn(is).setTitle(tit).setAuthor(aut).setYear(year).setAvaliable(disp).setPortada(port).setDataPublicacion(date);
            }
            return temp;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.print("La transacción se está revirtiendo");
                    con.rollback();
                } catch (SQLException excep) {
                    // Gestión de excepciones.
                }
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Book> getAll() {
        String selecc = "select * from Book";
        try (PreparedStatement ps = con.prepareStatement(selecc)) {
            ResultSet resultado = ps.executeQuery(selecc);
            List<Book> listaLibros = new ArrayList<>();
            while (resultado.next()) {
                Book temp = new Book();
                Integer identif = resultado.getInt("idBook");
                String is = resultado.getString("isbn");
                String tit = resultado.getString("titulo");
                String aut = resultado.getString("autor");
                int year = resultado.getInt("anho");
                boolean disp = resultado.getBoolean("disponible");
                byte[] port = resultado.getBytes("portada");
                LocalDate date = resultado.getDate("dataPublicacion").toLocalDate();
                temp.setIdBook(identif).setIsbn(is).setTitle(tit).setAuthor(aut).setYear(year).setAvaliable(disp).setPortada(port).setDataPublicacion(date);
                listaLibros.add(temp);
            }

            return listaLibros;

        } catch (SQLException e) {
            if (con != null) {

            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Book book) {
        String selecc = "insert into Book (isbn, titulo, autor, anho, disponible, portada) values (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = con.prepareStatement(selecc, Statement.RETURN_GENERATED_KEYS)){
            con.setAutoCommit(false);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getYear());
            ps.setBoolean(5, book.getAvaliable());
            Blob img = con.createBlob();
            byte [] b = book.getPortada();
            img.setBytes(1, b);
            ps.setBlob(6, img);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            while (keys.next()){

                Integer id = keys.getInt(1);
                book.setIdBook(id);
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }

            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {
        Integer id = book.getIdBook();
        deleteById(id);
    }

    @Override
    public boolean deleteById(int id) {
        String instrucc = "delete from Book where idBook=?";
        try (PreparedStatement ps = con.prepareStatement(instrucc)){
            con.setAutoCommit(false);
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    @Override
    public List<Integer> getAllIds() {
        String instrucc = "";
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