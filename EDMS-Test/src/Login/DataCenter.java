package Login;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataCenter {


    public static Connection DbConnector() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C://Users//HORD//IdeaProjects//EDMS-Test//src//Database.db");
            return conn;


        } catch (Exception e) {
            System.out.println("Connection Error");
        }
        return null;
    }


}
