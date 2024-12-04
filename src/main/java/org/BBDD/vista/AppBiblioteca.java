package org.BBDD.vista;
import org.BBDD.BookDAO;
import org.BBDD.ConnectionManager;
import org.BBDD.controller.BookController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppBiblioteca {
    public static void main(String[] args) {
        Connection con = ConnectionManager.getINSTANCE().getConnection();


        BookDAO bookDAO = new BookDAO(con);

        // Inicializar vista
        BookView bookView = new BookView();

        // Inicializar controlador
        BookController bookController = new BookController(bookDAO);

        // Vincular vista y controlador
        bookController.setView(bookView);
        bookView.setController(bookController);
        bookView.setVisible(true);


        /* PRUEBA DE INSERCIÓN DE LIBRO - ÉXITO
        try {
            con.setAutoCommit(false);
            String comando ="INSERT INTO Book (isbn, titulo, autor, anho, disponible)" +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = con.prepareStatement(comando)){
                st.setString(1, "9780307277672");
                st.setString(2, "Cien años de soledad");
                st.setString(3, "Gabriel García Márquez");
                st.setInt(4, 1967);
                st.setBoolean(5, true);
                st.executeUpdate();
                con.commit();

            }


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
*/
        /* PRUEBA DE INSERCIÓN DE PORTADA - ÉXITO
        * String portada = "C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\AD\\bookscovers\\cienaños.jpg";
        bd.updateLOBById(14, portada);
        * */

    }
}

