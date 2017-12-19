package eu.telecomnancy.pcd2k17.dbhandler;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDbhandler {

    public static void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/eu/telecomnancy/pcd2k17/database/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                //System.out.println(System.getProperty("user.dir"));
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/eu/telecomnancy/pcd2k17/database/test.db";

        String sql = "CREATE TABLE IF NOT EXISTS Assignments (\n"
                + "	title text PRIMARY KEY,\n"
                + "	description text,\n"
                + "	discipline text\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createFile() {
    	try {
            File dir = new File("src/main/resources/eu/telecomnancy/pcd2k17/database");
            dir.mkdir();
        }
        catch (Exception e){}
    }


    public static void main(String[] args) {
    	createFile();
        createNewDatabase("test.db");
        createNewTable();
    }
}