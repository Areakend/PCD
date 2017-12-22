package eu.telecomnancy.pcd2k17.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eu.telecomnancy.pcd2k17.dbhandler.MainDbhandler;


public class Groups extends MainDbhandler{
	
	public void createGroups() {
		String url = "jdbc:sqlite:"+ System.getProperty("user.dir") + 
				"/src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db";
		String sql = "CREATE TABLE IF NOT EXISTS Groups (\n"
				+ " name text,\n"
				+ " idStudent int,\n" //#Students.idStudent
				+ " CONSTRAINT Fk_idStudent FOREIGN KEY (idStudent) REFERENCES Students(idStudent)\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			//System.out.println("Groups table has been created.");
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
		}
				
	}
	
    public void insertGroup(String name, int idStudent) {
        String sql = "INSERT INTO Groups(name,idStudent) VALUES(?,?);";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, idStudent);
            pstmt.executeUpdate();
            //System.out.println("Group has been created.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void deleteGroup(String title) {
    	String sql = "DELETE FROM Groups WHERE title = ?";
    	try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1, title);
            pstmt.executeUpdate();
            //System.out.println("Group has been deleted.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void updateGroupName(String name, String newValue) {
        String sql = "UPDATE Groups SET name = ? "
                + "WHERE name = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, newValue);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            //System.out.println("Group has been updated.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void updateGroupComposition(String name, int newValue) {
        String sql = "UPDATE Groups SET idStudent = ? "
                + "WHERE name = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setInt(1, newValue);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            //System.out.println("Group has been updated.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void getAllGroups(){
        String sql = "SELECT * FROM assignments";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getString("name") +  "\t" + 
                                   rs.getInt("idStudent"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getGroupsby(String searchField, String Value){
        String sql = "SELECT * FROM Groups WHERE " + searchField + " = ?";
        try (Connection conn = this.connect();
        		PreparedStatement pstmt  = conn.prepareStatement(sql)){
        	pstmt.setString(1, Value);
        	ResultSet rs  = pstmt.executeQuery();
        	while (rs.next()) {
        		System.out.println(rs.getString("name") +  "\t" + 
                        		   rs.getInt("idStudent"));
        		}
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        		}
        }
    
	/*
	public static void main(String[] args) {
		// A faire d�s le lancement de l'appli
		MainDbhandler.createFile();
		MainDbhandler.createNewDatabase("gitTN.db");
		Groups assign = new Groups();
		assign.createGroups();
		// A faire d�s la cr�ation d'un nouveau devoir
		assign.insertGroup("title", "description", "discipline", "teacher", 1, 2, 
        		"prefix", "folder", "origine");
		assign.getAllGroups();
		// A faire d�s la modification d'un devoir
		assign.updateGroup("title","title","titre");
		assign.getGroupsby("teacher", "teacher");
		// A ne pas forc�ment faire
		assign.deleteGroup("titre");
	}*/

}
