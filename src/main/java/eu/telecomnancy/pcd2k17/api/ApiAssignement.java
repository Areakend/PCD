package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Project;

import java.util.List;

public class ApiAssignement {
    private int idAssign;
    private String name;
    private ApiConnect apico;
    private List<Project> lsp;
    private ProjectApi projectApi;

    public ApiAssignement(ApiConnect ac){
        this.apico = ac;
        this.projectApi = ac.getProjectApi();
        this.lsp = apico.getProjectsList(apico.login());
    }

    public void setName(String s){
        this.name = s;
    }

    public boolean createAssignement(String name){
        this.name = name;
        try {
            this.projectApi.createProject(name);
            System.out.println("Your project "+name+" has been created");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when creating project : The project "+name+" exists.");
        }
        return false;
    }

    public boolean deleteAssignement(int idProject){
        try {
            this.projectApi.deleteProject(idProject);
            System.out.println("Deletion Sucess");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when deleting a project : "+this.name+ " does not exist.");
        }

        return false;
    }

    public int getIdAssign(String name){
        this.name = name;
        for (int i = 0 ; i<this.lsp.size(); i++){
            if (this.lsp.get(i).getName().equals(name)){
                System.out.println("Assignement "+name+" Found.");
                this.idAssign = this.lsp.get(i).getId();
                return this.lsp.get(i).getId();
            }
        }
        return -1;
    }

    public boolean addMembers(int idProject, int userId, AccessLevel accessLevel){
        try {
            this.projectApi.addMember(idProject,userId,accessLevel);
            System.out.println("Member "+userId+" "+"added successfully");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when adding "+userId+".");
        }
        System.out.println("Assignement "+name+" Not Found.");
        return false;
    }

    //Default adding
    public boolean addMembers(int idProject, int userId){
        return this.addMembers(idProject,userId,AccessLevel.GUEST);
    }

    public boolean delMembers(int idProject, int userId){

        try {
            this.projectApi.removeMember(idProject,userId);
            System.out.println("Member "+userId+" "+"deleted successfully");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when deleting "+userId+".");
        }

        return false;
    }
}
