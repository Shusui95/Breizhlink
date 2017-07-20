package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Jdbc class
 */
public class Jdbc {

    private static Connection conn;

    public Jdbc() {
    }

    /**
     * Connect to DB
     * @return connection object
     */
    public Connection connect(){
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/javaee", "root", "root");
        }catch(Exception e){
            System.out.println("connect failed "+e.getStackTrace());
        }
        return conn;
    }

    /**
     * Close connection to DB
     * @param toBeClosed - conection object
     */
    public static void closeConnection( Connection toBeClosed ) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
