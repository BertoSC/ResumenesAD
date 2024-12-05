package org.BBDDfilosofos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConManager {
    private final static String URL="jdbc:h2:C:\\Users\\a23albertogc\\Desktop\\AD\\filosofos\\filosofosv2.3;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;DATABASE_TO_UPPER=FALSE";

    //private final static String URL = "jdbc:h2:C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\AD\\filosofos\\filosofosv2.3;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;DATABASE_TO_UPPER=FALSE";

    private final static String USER="";
    private final static String PASSWORD="";
    private static ConManager INSTANCE;
    private Connection con;

    private ConManager(){
        con = getConection();
    }

    public static ConManager getINSTANCE(){
        if (INSTANCE==null){
            synchronized (ConManager.class){
                if (INSTANCE==null){
                    INSTANCE= new ConManager();
                }
            }
        }
        return INSTANCE;
    }

    public Connection getConection() {
        try {
            this.con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("CONEXIÓN CHACHI MEU!");
            return con;
        } catch (SQLException e) {
            System.out.println("Va a ser que no :(");
            throw new RuntimeException(e);
        }
    }
}
