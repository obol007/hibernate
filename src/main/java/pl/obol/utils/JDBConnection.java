package pl.obol.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {

    private final static String URL = "jdbc:mysql://localhost:3306/bookStore?serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "coderslab";
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
       try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
        conn = connection;
       }catch (SQLException e){
           System.out.println("Błąd połączenia z bazą danych");
       }
        return conn;

    }


}
