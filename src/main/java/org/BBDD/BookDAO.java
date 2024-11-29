package org.BBDD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        String instrucc = "select idBook from Book";
        List<Integer> identificadores= new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(instrucc)){
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                Integer id=rs.getInt("idBook");
                identificadores.add(id);
            }
            return identificadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLOB(Book book, String f) {
        Integer id = book.getIdBook();
        updateLOBById(id, f);
    }

    @Override
    public void updateLOBById(int id, String f) {
        try {
            byte[] imagen = Files.readAllBytes(Paths.get(f));
            String instrucc = "update Book set portada=? where idBook=?";
            try (PreparedStatement ps = con.prepareStatement(instrucc)) {
                con.setAutoCommit(false);
                Blob blob = con.createBlob();
                blob.setBytes(1, imagen);
                ps.setBlob(1, blob);
                ps.setInt(2, id);
                int filasAct= ps.executeUpdate();
                if (filasAct==0){
                    System.out.println("No se ha actualizado ningún registro");
                }
                con.commit();
            } catch (SQLException e) {
                try{
                    con.rollback();
                    System.err.println("Error en la operación, revertimos cambios");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



   @Override
    public void deleteAll() {
        String isntr = "delete from Book";
        try(PreparedStatement st = con.prepareStatement(isntr)){
            con.setAutoCommit(false);
            int result= st.executeUpdate();
            if (result==0){
                System.err.println("No hay filas que eliminar");
            } else{
                System.out.println(result+ " filas eliminadas");
            }
            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("Error al realizar el rollback", ex);
            }
            throw new RuntimeException("Error al eliminar filas", e);
        }

   }
}