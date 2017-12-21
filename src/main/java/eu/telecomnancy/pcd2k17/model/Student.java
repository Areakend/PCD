package eu.telecomnancy.pcd2k17.model;

public class Student {

    private int idStudent;
    private String username;
    private String mail;
    private String year;
    private String appro;
    private String groupName;

    public Student() {
    }

    public Student(int idStudent, String username, String mail, String year,
    		String appro, String groupName) {
        this.idStudent = idStudent;
        this.username = username;
        this.mail = mail;
        this.year = year;
        this.appro = appro;
        this.groupName = groupName;
    }

    public void setIdStudent(int idStudent) {
    	this.idStudent = idStudent;
    }
    
    public int getIdStudent() {
        return idStudent;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public void setMail(String mail) {
    	this.mail = mail;
    }
    
    public String getMail() {
    	return mail;
    }
    
    public void setYear(String year) {
    	this.year = year ;
    }
    
    public String getYear() {
    	return year;
    }
    
    public void setAppro(String appro) {
    	this.appro = appro ;
    }
    
    public String getAppro() {
    	return appro;
    }
    
    public void setGroupName(String groupName) {
    	this.groupName = groupName;
    }
    
    public String getGroupName() {
    	return groupName;
    }
    
}