package eu.telecomnancy.pcd2k17.model;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import eu.telecomnancy.pcd2k17.api.ApiConnect;
import org.gitlab4j.api.models.User;

import eu.telecomnancy.pcd2k17.api.ApiUser;
import eu.telecomnancy.pcd2k17.dbhandler.MainDbhandler;


public class Teachers extends MainDbhandler{
	
    public static LinkedList<Integer>getlListTeacher(){
        LinkedList<Integer> listTeacherId = new LinkedList<>();
            listTeacherId.add(3);
            listTeacherId.add(5);
            listTeacherId.add(154);
            listTeacherId.add(120);
            listTeacherId.add(9);
            listTeacherId.add(329);
            listTeacherId.add(8);
            listTeacherId.add(6);
            listTeacherId.add(264);
            listTeacherId.add(7);
            listTeacherId.add(328);
            listTeacherId.add(2);
            return listTeacherId;
    }
	
	public void createTeachers() {
		String url = "jdbc:sqlite:"+ System.getProperty("user.dir") + 
				"/src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db";
		String sql = "CREATE TABLE IF NOT EXISTS Teachers (\n"
				+ " idTeacher int,\n"
				+ " username text,\n"
				+ " mail text,\n"
				+ " discipline text,\n"
                + " CONSTRAINT Fk_Teachers PRIMARY KEY (idTeacher,discipline)\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
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
        } catch (SQLException e) {
        }
    }
    
    public void deleteTeacher(int idTeacher) {
    	String sql = "DELETE FROM Teachers WHERE idTeacher = ?";
    	try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setInt(1, idTeacher);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // ATTENTION, SI UTILISE SUR MATIERE ELLE LES REMPLACERA TOUTES. DE PLUS ELLE N Y ARRIVERA PAS, LE COUPLE
    //(idTeacher,discipline) SERT DE CLE PRIMAIRE
    public void updateTeacher(int idTeacher, String updatedField, String newValue) {
        String sql = "UPDATE Teachers SET " + updatedField + " = ? "
                + "WHERE idTeacher = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, newValue);
            pstmt.setInt(2, idTeacher);
            pstmt.executeUpdate();
        } catch (SQLException e) {
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
    
    public void fillTeachersTable() {
        LinkedList<Integer> listTeacherId = Teachers.getlListTeacher();
    	Teachers teachers = new Teachers();

    	for (int i:listTeacherId){
    	    try {
                teachers.insertTeacher(i, ApiConnect.USER.getUser(i).getUsername(),
                        ApiConnect.USER.getUser(i).getUsername()+"@telecomnancy.eu", "");
            }
            catch(org.gitlab4j.api.GitLabApiException e){
            }
    	}
    	teachers.updateTeacher(3,"discipline","TOP");
        teachers.insertTeacher(3,"Olivier.Festor","Olivier.Festor@telecomnancy.eu","SD");
        teachers.updateTeacher(5,"discipline","MOCI");
        teachers.insertTeacher(5, "Francois.Charoy","Francois.Charoy@telecomnancy.eu","PCD");
        teachers.updateTeacher(154,"discipline","SD");
        teachers.updateTeacher(120,"discipline","TOP");
        teachers.updateTeacher(9,"discipline","TOP");
        teachers.updateTeacher(329,"discipline","MOCI");
        teachers.insertTeacher(329,"Martine.Gautier","Martine.Gautier@telecomnancy.eu","PCD");
        teachers.updateTeacher(8,"discipline","PCD");
        teachers.updateTeacher(6,"discipline","MOCI");
        teachers.insertTeacher(6,"Gerald.Oster","Gerald.Oster@telecomnancy.eu","PCD");
        teachers.updateTeacher(264,"discipline","TOP");
        teachers.insertTeacher(264,"Sebastien.Dasila","Sebastien.Dasila@telecomnancy.eu","SD");
        teachers.updateTeacher(7,"discipline","RS");
        teachers.updateTeacher(328,"discipline","PCD");
        teachers.insertTeacher(328,"Brigitte.Wrobel-dautcourt","Brigitte.Wrobel-dautcourt@te","PCD");
    }

	/*
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
	}*/

}
