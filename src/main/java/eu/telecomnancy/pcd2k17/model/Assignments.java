package eu.telecomnancy.pcd2k17.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import eu.telecomnancy.pcd2k17.dbhandler.MainDbhandler;


public class Assignments extends MainDbhandler{
	
	public void createAssignments() {
		String url = "jdbc:sqlite:"+ System.getProperty("user.dir") + "/src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db";
		
		String sql = //"CREATE TYPE state IF NOT EXISTS AS ENUM ('active', 'closed', 'archived');\n"
				//"CREATE TYPE isPrivate IF NOT EXISTS AS ENUM ('true','false');\n"
				"CREATE TABLE IF NOT EXISTS Assignments (\n"
				+ " title text PRIMARY KEY,\n"
				+ " description text,\n"
				+ " discipline text,\n"
				+ " teacher text,\n"
				+ " releaseDate int,\n"
				+ " deadline int,\n"
				//+ tous les groupes de projet
				+ " prefix text,\n"
				//+ " currentState state,\n"
				+ " folder text,\n"
				//+ " privateGit isPrivate,\n"
				+ " origine text\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Assignments table has been created.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
				
	}
	
    public void insertAssignment(String title, String description, String discipline, String teacher,
    		int releaseDate, int deadline, String prefix, String folder, String origine) {
        String sql = "INSERT INTO Assignments(title,description,discipline,teacher,releaseDate,"
        		+ "deadline,prefix,folder,origine) VALUES(?,?,?,?,?,?,?,?,?)";
        System.out.println("1");
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	System.out.println("3");
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, discipline);
            pstmt.setString(4, teacher);
            pstmt.setInt(5, releaseDate);
            pstmt.setInt(6, deadline);
            pstmt.setString(7, prefix);
            pstmt.setString(8, folder);
            pstmt.setString(9, origine);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static void main(String[] args) {
		MainDbhandler.createFile();
		MainDbhandler.createNewDatabase("gitTN.db");
		Assignments assign = new Assignments();
		assign.createAssignments();
        assign.insertAssignment("title", "description", "discipline", "teacher", 1, 2, 
        		"prefix", "folder", "origine");
	}

}
