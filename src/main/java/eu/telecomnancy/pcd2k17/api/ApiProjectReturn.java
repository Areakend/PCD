package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.TreeItem;

import java.util.LinkedList;
import java.util.List;

public class ApiProjectReturn extends ApiListProjectReturn{
    private String name;
    private Project project;
    private ApiAssignment assign;

    public ApiProjectReturn(String s, ApiAssignment assign_){
        super();
        this.assign = assign_;
        this.name = s;
        this.project = this.assign.getProject(s);
        this.checkProject();
    }

    private void checkProject(){
        if(this.project == null){
            this.createProject(name,this.assign.getAssignmentId());
            this.project = this.assign.getProject(this.name);
        }
    }

    public void create(){
        if (this.project.getId() == -1){
            this.createProject(this.name,this.assign.getAssignmentId());
        }
        else {
            System.out.println("The assignment "+this.name+" exists already.");
        }
    }

    public void delete(){
        this.deleteProject(this.project.getId());
    }

    public int getIdProject(){
        for (Project p: this.assign.getListProjects()) {
            if(p.getName().equals(this.name)){
                return p.getId();
            }
        }
        return -1;
    }

    public boolean addMembers(int idProject, int userId, AccessLevel accessLevel){
        try {
            ApiConnect.PROJECT.addMember(idProject,userId,accessLevel);
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
            ApiConnect.PROJECT.removeMember(idProject,userId);
            System.out.println("Member "+userId+" "+"deleted successfully");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when deleting "+userId+".");
        }
        return false;
    }

    public String getName(){
        return this.name;
    }

    public void saveAssignment(ApiAssignmentFileManager fileManager){
        List<TreeItem> listItem = fileManager.getElements();
        for (TreeItem item: listItem) {
            fileManager.saveFile("master",item.getName());
        }
    }

}
