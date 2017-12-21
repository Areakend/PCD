package eu.telecomnancy.pcd2k17.dbhandler;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import eu.telecomnancy.pcd2k17.model.*;
import eu.telecomnancy.pcd2k17.api.*;

public class MainDbhandler {

    private static void createNewDatabase() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + 
        		"/src/main/resources/eu/telecomnancy/pcd2k17/database/GitTN.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("The database GitTN.db has been created.");
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
    
    private static void createFile() {
    	try {
            File dir = new File("src/main/resources/eu/telecomnancy/pcd2k17/database");
            dir.mkdir();
            System.out.println("The file has been created.");
        }
        catch (Exception e){
        	System.out.println("The file already exists.");
        }
    }
    
    private static void deleteDb() {
    	Path path = FileSystems.getDefault()
    			.getPath("src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db");
    	try {
			Files.deleteIfExists(path);
			System.out.println("The database gitTN.db has been deleted.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("The database gitTN.db does not exist.");
		}
	}
    
    /*public static void refreshFromApi() {
    	ApiUser user = new ApiUser();
    	List<User>
    	
    }*/
    
    public static void main(String[] args) {
    	deleteDb();
    	createFile();
    	createNewDatabase();
		Teachers teachers = new Teachers();
		teachers.createTeachers();
		Students students = new Students();
		students.createStudents();
		Groups groups = new Groups();
		groups.createGroups();
		Assignments assignments = new Assignments();
		assignments.createAssignments();
    }
    
}