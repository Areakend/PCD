package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.ProjectApi;
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
    }

    public void setName(String s){
        this.name = s;
    }

    public boolean checkAssignement(){
        this.lsp = apico.getProjectsList(apico.login());
        for (int i = 0 ; i<this.lsp.size(); i++){
            if (this.lsp.get(i).getName().equals(this.name)){
                System.out.println("Assignement "+name+" Found.");
                this.idAssign = this.lsp.get(i).getId();
                return true;
            }
        }
        System.out.println("Assignement "+name+" Not Found.");
        return false;
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
                return this.lsp.get(i).getId();
            }
        }
        return -1;
    }

    public boolean addMembers(int idProject, int userId, int accessLevel){
        try {
            this.projectApi.addMember(idProject,userId,accessLevel);
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when removing "+userId+".");
        }

        return false;
    }
}
