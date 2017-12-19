package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Project;

public class ApiAssignment implements ApiAssignmentInterface{
    private String name;
    private Project project;
    private int idAssign;
    private ApiListAssignment listAssignment;

    public ApiAssignment(String s){
        this.listAssignment = ApiListAssignment.getInstance();
        this.name = s;
        this.idAssign = this.listAssignment.getIdAssign(s);
        if (this.idAssign == -1){
            System.out.println("This assignment doesn't exist yet. Please create by using this.create().");
        }
        else {
            try{
                this.project = this.listAssignment.projectApi.getProject(idAssign);
            }
            catch (org.gitlab4j.api.GitLabApiException e){System.out.println("Internal Error : Project not found.");}
        }
    }

    public void create(){
        if (this.idAssign == -1){
            this.listAssignment.createAssignment(this.name);
            this.refresh();
        }
        else {
            System.out.println("The assignment "+this.name+" exists already.");
        }
    }

    public void delete(){
        this.listAssignment.deleteAssignment(this.idAssign);
        this.refresh();
    }

    public boolean addMembers(int idProject, int userId, AccessLevel accessLevel){
        try {
            this.listAssignment.projectApi.addMember(idProject,userId,accessLevel);
            System.out.println("Member "+userId+" "+"added successfully");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when adding "+userId+".");
        }
        System.out.println("Assignment "+name+" Not Found.");
        return false;
    }

    //Default adding
    public boolean addMembers(int idProject, int userId){
        return this.addMembers(idProject,userId,AccessLevel.GUEST);
    }

    public boolean delMembers(int idProject, int userId){
        try {
            this.listAssignment.projectApi.removeMember(idProject,userId);
            System.out.println("Member "+userId+" "+"deleted successfully");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when deleting "+userId+".");
        }
        return false;
    }

    public int getIdAssign(){
        return this.listAssignment.getIdAssign(this.name);
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String desc){
        this.project.setDescription(desc);
    }

    public String getDescription(){
        return this.project.getDescription();
    }

    public void refresh(){
        this.listAssignment.refresh();
        this.idAssign = this.listAssignment.getIdAssign(this.name);
        if (this.idAssign == -1){
            System.out.println("This assignment doesn't exist yet. Please create by using this.create().");
        }
        else {
            try{
                this.project = this.listAssignment.projectApi.getProject(idAssign);
            }
            catch (org.gitlab4j.api.GitLabApiException e){}
        }
    }

}
