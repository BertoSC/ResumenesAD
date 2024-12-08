package org.BDEjemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManEj {
    private final static String URL= "jdbc:h2:C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\AD\\filosofos\\filosofosv2.3;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;DATABASE_TO_UPPER=FALSE";
    private final static String USER= "";
    private static final String PASSWORD ="";
    private Connection con;
    private static ConnectionManEj INSTANCE;

    private ConnectionManEj(){
        getConnection();
    }


    public static ConnectionManEj getINSTANCE(){
        if (INSTANCE==null) {
            synchronized (ConnectionManEj.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionManEj();
                }
            }
        }
            return INSTANCE;

    }

    private Connection getConnection(){
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
