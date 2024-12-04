package org.BBDDfilosofos;

import org.BBDDfilosofos.vista.FilosofosApp;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = ConManager.getINSTANCE().getConection();
        FilosofosApp app = new FilosofosApp(con);
        app.show();

    }
}
