package letstrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class javaconnect {

    public static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static Connection conn ;
    public PreparedStatement pst = null;
String curentdir = System.getProperty("user.dir");
    public static Connection Connecrdb() {
        try {

            Class.forName(JDBC_DRIVER);

            javaconnect.conn = DriverManager.getConnection("jdbc:sqlite:LetsTrade.db");
           // javaconnect.conn = DriverManager.getConnection("jdbc:sqlite:D:\\Projects\\LetsTrade\\LetsTrade.db");
          //  javaconnect.conn = DriverManager.getConnection("jdbc:sqlite:currentdir");

           // JOptionPane.showMessageDialog(null, "Connection established");

            //return conn;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return conn;

    }

    public static void disconnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null, "Where is my jdbc driver");
            e.printStackTrace();
            throw e;

        }

    }

}
