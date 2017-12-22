package eu.telecomnancy.pcd2k17.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eu.telecomnancy.pcd2k17.dbhandler.MainDbhandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Students extends MainDbhandler{

	public void createStudents() {
		String url = "jdbc:sqlite:"+ System.getProperty("user.dir") + 
				"/src/main/resources/eu/telecomnancy/pcd2k17/database/gitTN.db";
		String sql = "CREATE TABLE IF NOT EXISTS Students (\n"
				+ " idStudent int PRIMARY KEY,\n"
				+ " username text,\n"
				+ " mail text,\n"
				+ " year text,\n"
				+ " appro text\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			//System.out.println("Students table has been created.");
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
		}
				
	}
	
    public void insertStudent(int idStudent, String username, String mail,
    		String year, String appro) {
        String sql = "INSERT INTO Students(idStudent,username,mail,year,appro)"
                + " VALUES(?,?,?,?,?);";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idStudent);
            pstmt.setString(2, username);
            pstmt.setString(3, mail);
            pstmt.setString(4, year);
            pstmt.setString(5, appro);
            pstmt.executeUpdate();
            //System.out.println("Student has been created.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void insertStudent(int idStudent, String username, String mail,
    		String year, String appro, String groupName) {
        String sql = "INSERT INTO Students(idStudent,username,mail,year,appro)"
        		+ " VALUES(?,?,?,?,?);";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setInt(1, idStudent);
            pstmt.setString(2, username);
            pstmt.setString(3, mail);
            pstmt.setString(4, year);
            pstmt.setString(5, appro);
            pstmt.executeUpdate();
            Groups groups = new Groups();
            groups.insertGroup(groupName,idStudent);
            //System.out.println("Student has been created.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void deleteStudent(int idStudent) {
    	String sql = "DELETE FROM Students WHERE idStudent = ?";
    	try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setInt(1, idStudent);
            pstmt.executeUpdate();
            //System.out.println("Student has been deleted.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public void updateStudent(int idStudent, String updatedField, String newValue) {
        String sql = "UPDATE Students SET " + updatedField + " = ? "
                + "WHERE idStudent = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, newValue);
            pstmt.setInt(2, idStudent);
            pstmt.executeUpdate();
            //System.out.println("Assignment has been updated.");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
    
    public ObservableList<Student>  getAllStudents(){
        ObservableList<Student> studentData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Students JOIN Groups where Students.idStudent == Groups.idStudent";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                String prenom,nom,mail,groupe,username;

                System.out.println(rs.getInt("idStudent") +  "\t" + 
                				   rs.getString("username") + "\t" +
                                   rs.getString("mail") + "\t" +
                                   rs.getString("year") + "\t" +
                                   rs.getString("appro"));
                username = rs.getString("username");
                String[] prenomNom = username.split("\\.");
                if (prenomNom.length ==2) {
                    prenom = prenomNom[0];
                    nom = prenomNom[1];
                } else {
                    System.out.print(""+prenomNom.length);
                    prenom = "";
                    nom = "";
                }
                mail = rs.getString("mail");
                groupe = rs.getString("name");
                Student student = new Student(prenom,nom,mail,groupe);
                student.afficherStudent();
                studentData.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentData;
    }
    
    public void getStudentsby(String searchField, String Value){
        String sql = "SELECT * FROM Students WHERE " + searchField + " = ?";
        try (Connection conn = this.connect();
        		PreparedStatement pstmt  = conn.prepareStatement(sql)){
        	pstmt.setString(1, Value);
        	ResultSet rs  = pstmt.executeQuery();
        	while (rs.next()) {
        		System.out.println(rs.getInt("idStudent") +  "\t" + 
        						   rs.getString("username") +  "\t" + 
                        		   rs.getString("mail") + "\t" +
                        		   rs.getString("year") + "\t" +
                        		   rs.getString("appro"));
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
		Students assign = new Students();
		assign.createStudents();
		// A faire d�s la cr�ation d'un nouveau devoir
		//assign.insertStudent("name", "firstName", "mail", "discipline");
		//assign.getAllStudents();
		// A faire d�s la modification d'un devoir
		assign.updateStudent("mail","name","theName");
		assign.getStudentsby("name", "theName");
		// A ne pas forc�ment faire
		assign.deleteStudent("theName");
	}*/


}
