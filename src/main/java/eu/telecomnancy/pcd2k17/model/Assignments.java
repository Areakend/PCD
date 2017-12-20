package eu.telecomnancy.pcd2k17.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eu.telecomnancy.pcd2k17.dbhandler.MainDbhandler;


public class Assignments extends MainDbhandler{
	
	public void createAssignments() {
		String url = "jdbc:sqlite:"+ System.getProperty("user.dir") + 
				"/src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db";
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
        		+ "deadline,prefix,folder,origine) VALUES(?,?,?,?,?,?,?,?,?);";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
            System.out.println("Assignment has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteAssignment(String title) {
    	String sql = "DELETE FROM Assignments WHERE title = ?";
    	try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1, title);
            pstmt.executeUpdate();
            System.out.println("Assignment has been deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //La fonction suivante ne permet de changer la valeur que des Strings
    public void updateAssignment(String title, String updatedField, String newValue) {
        String sql = "UPDATE Assignments SET " + updatedField + " = ? "
                + "WHERE title = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, newValue);
            pstmt.setString(2, title);
            pstmt.executeUpdate();
            System.out.println("Assignment has been updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getAllAssignments(){
        String sql = "SELECT * FROM assignments";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getString("title") +  "\t" + 
                                   rs.getString("description") + "\t" +
                                   rs.getString("discipline") + "\t" +
                                   rs.getString("teacher") + "\t" +
                                   rs.getInt("releaseDate") + "\t" +
                                   rs.getInt("deadline") + "\t" +
                                   rs.getString("prefix") + "\t" +
                                   rs.getString("folder") + "\t" +
                                   rs.getString("origine"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //La fonction suivante ne permet de chercher la valeur qu'à travers des Strings
    public void getAssignmentsby(String searchField, String Value){
        String sql = "SELECT * FROM Assignments WHERE " + searchField + " = ?";
        try (Connection conn = this.connect();
        		PreparedStatement pstmt  = conn.prepareStatement(sql)){
        	pstmt.setString(1, Value);
        	ResultSet rs  = pstmt.executeQuery();
        	while (rs.next()) {
        		System.out.println(rs.getString("title") +  "\t" + 
                        		   rs.getString("description") + "\t" +
                        		   rs.getString("discipline") + "\t" +
                        		   rs.getString("teacher") + "\t" +
                        		   rs.getInt("releaseDate") + "\t" +
                        		   rs.getInt("deadline") + "\t" +
                        		   rs.getString("prefix") + "\t" +
                        		   rs.getString("folder") + "\t" +
                        		   rs.getString("origine"));
        		}
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        		}
        }
    
	
	public static void main(String[] args) {
		// A faire dès le lancement de l'appli
		MainDbhandler.createFile();
		MainDbhandler.createNewDatabase("gitTN.db");
		Assignments assign = new Assignments();
		assign.createAssignments();
		// A faire dès la création d'un nouveau devoir
		assign.insertAssignment("title", "description", "discipline", "teacher", 1, 2, 
        		"prefix", "folder", "origine");
		assign.getAllAssignments();
		// A faire dès la modification d'un devoir
		assign.updateAssignment("title","title","titre");
		assign.getAssignmentsby("teacher", "teacher");
		// A ne pas forcément faire
		assign.deleteAssignment("titre");
	}

}
