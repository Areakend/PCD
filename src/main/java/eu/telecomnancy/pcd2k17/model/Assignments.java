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
		String sql = "CREATE TABLE IF NOT EXISTS Assignments (\n"
				+ " idAssignment int PRIMARY KEY,\n"
				+ " title text,\n"
				+ " description text,\n"
				+ " discipline text,\n" //#Teachers.discipline
				+ " releaseDate text,\n"
				+ " deadline text,\n"
				+ " deadlineHour text,\n"
				+ " groupName text,\n" //#Groups.groupName
				+ " state text,\n"
				+ " folder text,\n"
				+ " privateGit text,\n"
				+ " origine text,\n"
				+ " studentsCapacity int\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Assignments table has been created.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
				
	}
	
    public void insertAssignment(int idAssignment, String title, String description, String discipline,
    		String releaseDate, String deadline, String deadlineHour, String groupName, String state,
    		String folder, String privateGit, String origine, int studentsCapacity) {
        String sql = "INSERT INTO Assignments(idAssignment,title,description,discipline,"
        		+ "releaseDate,deadline,deadlineHour,groupName,state,folder,privateGit,"
        		+ "origine,studentCapacity) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setInt(1, idAssignment);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setString(4, discipline);
            pstmt.setString(5, releaseDate);
            pstmt.setString(6, deadline);
            pstmt.setString(7, deadlineHour);
            pstmt.setString(8, groupName);
            pstmt.setString(9, state);
            pstmt.setString(10, folder);
            pstmt.setString(11, privateGit);
            pstmt.setString(12, origine);
            pstmt.setInt(13, studentsCapacity);
            pstmt.executeUpdate();
            System.out.println("Assignment has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteAssignment(int idAssignment) {
    	String sql = "DELETE FROM Assignments WHERE idAssignment = ?";
    	try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setInt(1, idAssignment);
            pstmt.executeUpdate();
            System.out.println("Assignment has been deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Ne permet pas de changer la taille des groupes
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
                System.out.println(rs.getInt("idAssignment") +  "\t" + 
                				   rs.getString("title") +  "\t" + 
                                   rs.getString("description") + "\t" +
                                   rs.getString("discipline") + "\t" +
                                   rs.getString("releaseDate") + "\t" +
                                   rs.getString("deadline") + "\t" +
                                   rs.getString("deadlineHour") + "\t" +
                                   rs.getString("groupName") + "\t" +
                                   rs.getString("state") + "\t" +
                                   rs.getString("folder") + "\t" +
                                   rs.getString("privateGit") + "\t" +
                                   rs.getString("origine") + "\t" +
                                   rs.getInt("studentsCapacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getAssignmentsby(String searchField, String Value){
        String sql = "SELECT * FROM Assignments WHERE " + searchField + " = ?";
        try (Connection conn = this.connect();
        		PreparedStatement pstmt  = conn.prepareStatement(sql)){
        	pstmt.setString(1, Value);
        	ResultSet rs  = pstmt.executeQuery();
        	while (rs.next()) {
        		System.out.println(rs.getInt("idAssignment") +  "\t" + 
     				   			   rs.getString("title") +  "\t" + 
     				   			   rs.getString("description") + "\t" +
     				   			   rs.getString("discipline") + "\t" +
     				   			   rs.getString("releaseDate") + "\t" +
     				   			   rs.getString("deadline") + "\t" +
     				   			   rs.getString("deadlineHour") + "\t" +
     				   			   rs.getString("groupName") + "\t" +
     				   			   rs.getString("state") + "\t" +
     				   			   rs.getString("folder") + "\t" +
     				   			   rs.getString("privateGit") + "\t" +
     				   			   rs.getString("origine") + "\t" +
     				   			   rs.getInt("studentsCapacity"));
        		}
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        		}
        }
    
	
	public static void main(String[] args) {
		/*// A faire dès le lancement de l'appli
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
		assign.deleteAssignment("titre");*/
	}

}
