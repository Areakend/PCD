/*package eu.telecomnancy.pcd2k17.model;

import java.util.Date;
import java.util.List;

public class Assignment {
	
	private String title;
	private String description;
	private String discipline;
	private String teacher;
	private Date deadline;
	private List<Group> groups;
	private String prefix; // prefixe au nom de tous les projets
	private String state;
	private String folder;
	private boolean privateGit; // depot prive ou non
	private boolean origine; //clone a partir d'un depot d'origine ? Si oui, URL
	private String origineUrl;
	
	public Assignment(String title) {
		this.title = title;
		
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDiscipline(String discipline){
		this.discipline=discipline;
	}
	
	public String getDiscipline(){
		return this.discipline;
	}
	
	public void setTeacher(String teacher){
		this.teacher=teacher;
	}
	
	public String getTeacher(){
		return this.teacher;
	}
	
	public void setDeadline(Date deadline){
		this.deadline=deadline;
	}
	
	public Date getDeadline(){
		return this.deadline;
	}
	
	public void addGroup(Group group){
		this.groups.add(group);
	}
	
	public void removeGroup(List<Group> group){
		this.groups.remove(group);
	}
	
	public List<Group> getGroups(){
		return this.groups;
	}
	
	public void setPrefix(String prefix){
		this.prefix=prefix;
	}
	
	public String getPrefix(){
		return this.prefix;
	}
	
	public void setState(String state) {
		if (state.equals("active") || state.equals("closed") || state.equals("archived")) {
			this.state = state;
		}
		else {
			System.out.println("error, choices : 'active', 'closed' or 'archived' ");
		}
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	public String getFolder() {
		return this.folder;
	}
	
	public void setPrivateGit(boolean privateGit) {
		this.privateGit=privateGit;
	}
	
	public boolean getPrivateGit() {
		return this.privateGit;
	}
	
	public void setOrigine() {
		this.origine = false ;
	}
	
	public void setOrigine(String origineUrl) {
		this.origine = true ;
		this.origineUrl = origineUrl ;
	}
	
	public String getOrigine() {
		if (origine) {
			return this.origineUrl ;
		}
		else {
			return null;
		}
		
	}

}
*/