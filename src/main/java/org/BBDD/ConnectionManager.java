package org.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager INSTANCE;
    private Connection con;
    private static final String USUARIO="";
    private static final String CONTRASEÑA="";
    private static final String URL="jdbc:h2:C:\\Users\\a23albertogc\\Desktop\\AD\\biblioteca2";
    // PARA USAR EN CASAprivate static final String URL="jdbc:h2:D:\\VSPC-BLACKFRIDAY\\biblioteca2";

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

    public void getConnection(){
        try {
            con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            //log.info("Conexión exitosa");  revisar de qué va esto del log
            System.out.println("Conexión chachi");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        ConnectionManager cn = getINSTANCE();
    }


}
