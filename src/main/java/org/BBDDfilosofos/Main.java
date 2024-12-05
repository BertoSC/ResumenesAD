package org.BBDDfilosofos;

import org.BBDDfilosofos.vista.FilosofosApp;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        FilosofosApp app = new FilosofosApp();
        app.show();

    }
}
