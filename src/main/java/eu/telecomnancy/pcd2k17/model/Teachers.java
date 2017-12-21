package eu.telecomnancy.pcd2k17.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eu.telecomnancy.pcd2k17.dbhandler.MainDbhandler;


public class Teachers extends MainDbhandler{
	
	public void createTeachers() {
		String url = "jdbc:sqlite:"+ System.getProperty("user.dir") + 
				"/src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db";
		String sql = "CREATE TABLE IF NOT EXISTS Teachers (\n"
				+ " idTeacher int PRIMARY KEY,\n"
				+ " username text,\n"
				+ " mail text,\n"
				+ " discipline text\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Teachers table has been created.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
				
	}
	
    public void insertTeacher(int idTeacher, String username, String mail,
    		String discipline) {
        String sql = "INSERT INTO Teachers(idTeacher,username,mail,discipline)"
        		+ " VALUES(?,?,?,?);";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setInt(1, idTeacher);
            pstmt.setString(2, username);
            pstmt.setString(3, mail);
            pstmt.setString(4, discipline);
            pstmt.executeUpdate();
            System.out.println("Teacher has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteTeacher(int idTeacher) {
    	String sql = "DELETE FROM Teachers WHERE idTeacher = ?";
    	try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setInt(1, idTeacher);
            pstmt.executeUpdate();
            System.out.println("Teacher has been deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateTeacher(int idTeacher, String updatedField, String newValue) {
        String sql = "UPDATE Teachers SET " + updatedField + " = ? "
                + "WHERE idTeacher = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, newValue);
            pstmt.setInt(2, idTeacher);
            pstmt.executeUpdate();
            System.out.println("Assignment has been updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getAllTeachers(){
        String sql = "SELECT * FROM Teachers";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getInt("idTeacher") +  "\t" + 
                				   rs.getString("username") + "\t" +
                                   rs.getString("mail") + "\t" +
                                   rs.getString("discipline"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getTeachersby(String searchField, String Value){
        String sql = "SELECT * FROM Teachers WHERE " + searchField + " = ?";
        try (Connection conn = this.connect();
        		PreparedStatement pstmt  = conn.prepareStatement(sql)){
        	pstmt.setString(1, Value);
        	ResultSet rs  = pstmt.executeQuery();
        	while (rs.next()) {
        		System.out.println(rs.getInt("idTeacher") +  "\t" + 
        						   rs.getString("username") +  "\t" + 
                        		   rs.getString("mail") + "\t" +
                        		   rs.getString("discipline"));
        		}
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        		}
        }
    
	/*
	public static void main(String[] args) {
		// A faire dès le lancement de l'appli
		MainDbhandler.createFile();
		MainDbhandler.createNewDatabase("gitTN.db");
		Teachers assign = new Teachers();
		assign.createTeachers();
		// A faire dès la création d'un nouveau devoir
		//assign.insertTeacher("name", "firstName", "mail", "discipline");
		//assign.getAllTeachers();
		// A faire dès la modification d'un devoir
		assign.updateTeacher("mail","name","theName");
		assign.getTeachersby("name", "theName");
		// A ne pas forcément faire
		assign.deleteTeacher("theName");
	}*/

}
