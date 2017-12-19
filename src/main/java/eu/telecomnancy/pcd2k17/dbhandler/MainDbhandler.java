package eu.telecomnancy.pcd2k17.dbhandler;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDbhandler {

    public static void createNewDatabase(String fileName) {
    	createFile();
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + 
        		"/src/main/resources/eu/telecomnancy/pcd2k17/database/" + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("The database " + fileName + " has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Connection connect() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + 
        		"/src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public static void createFile() {
    	try {
            File dir = new File("src/main/resources/eu/telecomnancy/pcd2k17/database");
            dir.mkdir();
        }
        catch (Exception e){}
    }
    
    
}