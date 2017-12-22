package eu.telecomnancy.pcd2k17.dbhandler;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import eu.telecomnancy.pcd2k17.model.*;
import eu.telecomnancy.pcd2k17.api.*;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

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
    
    public static Connection connect() {
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
    
    public static void refreshFromApi() {
        ApiUser user = new ApiUser();
        Students students = new Students();
        Groups groups = new Groups();
        try {
            List<Project> projectsList = ApiConnect.PROJECT.getProjects();
            int idProject = 0;
            for (Project p : projectsList) {
                idProject = p.getId();
                List<Member> listMembers = ApiConnect.PROJECT.getMembers(idProject);
                for (Member m : listMembers) {
                    if (!Teachers.getlListTeacher().contains(m.getId())) {
                        students.insertStudent(m.getId(), m.getUsername(), m.getUsername() + "@telecomnancy.eu",
                                "", "", p.getName());
                    }
                    groups.insertGroup(p.getName(),m.getId());
                }
            }
            System.out.println("end Students with projets");
        } catch (org.gitlab4j.api.GitLabApiException e) {
            System.out.println("Can't get projectList." + e);
        }
        for (User u : user.getUsers()) {
            if (!Teachers.getlListTeacher().contains(u.getId())) {
                students.insertStudent(u.getId(), u.getUsername(), u.getUsername() + "@telecomnancy.eu", "", "");
            }
        }
        System.out.println("end Students");
        /*Assignments assignments = new Assignments();
        try {
            List<Group> groupList = ApiConnect.GROUP.getGroups();
            int i =0;
            for (Group g: groupList) {
                i=0;
                if (g.getFullName().contains("/")) {
                    while (g.getFullName().charAt(i) != '/'){
                        i++;
                    }
                    String discipline = g.getFullName().substring(0,i-1);
                    System.out.println("discipline = " + discipline);
                    List<Project> listProj = g.getProjects();
                    if (!listProj.isEmpty()){
                        for(Project s :listProj){
                            assignments.insertAssignment(g.getId(),g.getName(),g.getDescription(),discipline,"",
                                    "","",s.getName(),"active","","true","",150);
                        }
                    }
                }
            }
            System.out.println("end Assignments");
        }
        catch (org.gitlab4j.api.GitLabApiException e) {
            System.out.println("Internal Error : Can't insert assignments in Db" + e );
        }*/

    }
    
    private static void rebootDb() {
    	deleteDb();
    	createFile();
    	createNewDatabase();
		Teachers teachers = new Teachers();
		teachers.createTeachers();
		teachers.fillTeachersTable();
		Students students = new Students();
		students.createStudents();
		Groups groups = new Groups();
		groups.createGroups();
		Assignments assignments = new Assignments();
		assignments.createAssignments();
    }

    public static void main(String[] args) {
        ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
        api.login("kB6hEpzAfzsZ33yqUVVd");
        rebootDb();
        refreshFromApi();

    }
    
}