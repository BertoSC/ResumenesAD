package org.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager INSTANCE;
    // para conectarse a BD necesitamos un objeto de tipo Connection
    private Connection con;
    private static final String USUARIO="";
    private static final String CONTRASEÑA="";
    private static final String URL="jdbc:h2:C:\\Users\\a23albertogc\\Desktop\\AD\\biblioteca2;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;DATABASE_TO_UPPER=FALSE";
   // private static final String URL="jdbc:h2:D:\\VSPC-BLACKFRIDAY\\biblioteca2;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;DATABASE_TO_UPPER=FALSE";

    private ConnectionManager(){
       getConnection();
    }

    public static ConnectionManager getINSTANCE() {
        if (INSTANCE==null){
            synchronized (ConnectionManager.class){
                if(INSTANCE==null){
                    INSTANCE= new ConnectionManager();
                }
            }
        }

        return INSTANCE;
    }

    public Connection getConnection(){
        try {
            // para establecer conexión usamos la clase DriverManager, con varias formas de ser llamada
            con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            //log.info("Conexión exitosa");  revisar de qué va esto del log

            System.out.println("Conexión chachi");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}
