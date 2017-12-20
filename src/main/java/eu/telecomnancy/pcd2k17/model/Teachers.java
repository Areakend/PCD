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
				+ " name text,\n"
				+ " firstName text,\n"
				+ " mail text ,\n"
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
	
    public void insertTeacher(String name, String firstName, String mail, String discipline) {
        String sql = "INSERT INTO Teachers(name,firstName,mail,discipline)"
        		+ " VALUES(?,?,?,?);";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, firstName);
            pstmt.setString(3, mail);
            pstmt.setString(4, discipline);
            pstmt.executeUpdate();
            System.out.println("Teacher has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteTeacher(String mail) {
    	String sql = "DELETE FROM Teachers WHERE mail = ?";
    	try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1, mail);
            pstmt.executeUpdate();
            System.out.println("Teacher has been deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateTeacher(String mail, String updatedField, String newValue) {
        String sql = "UPDATE Teachers SET " + updatedField + " = ? "
                + "WHERE mail = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, newValue);
            pstmt.setString(2, mail);
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
                System.out.println(rs.getString("name") +  "\t" + 
                                   rs.getString("firstName") + "\t" +
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
        		System.out.println(rs.getString("name") +  "\t" + 
                        		   rs.getString("firstName") + "\t" +
                        		   rs.getString("mail") + "\t" +
                        		   rs.getString("discipline"));
        		}
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        		}
        }
    
	
	public static void main(String[] args) {
		// A faire d�s le lancement de l'appli
		MainDbhandler.createFile();
		MainDbhandler.createNewDatabase("gitTN.db");
		Teachers assign = new Teachers();
		assign.createTeachers();
		// A faire d�s la cr�ation d'un nouveau devoir
		//assign.insertTeacher("name", "firstName", "mail", "discipline");
		//assign.getAllTeachers();
		// A faire d�s la modification d'un devoir
		assign.updateTeacher("mail","name","theName");
		assign.getTeachersby("name", "theName");
		// A ne pas forc�ment faire
		assign.deleteTeacher("theName");
	}

}
