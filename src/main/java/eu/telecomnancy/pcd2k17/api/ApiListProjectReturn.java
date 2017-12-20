package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.Project;

import java.util.List;

public class ApiListProjectReturn {
    private static ApiListProjectReturn ala;
    private int idAssign;
    private ApiConnect apico;
    private List<Project> lsp;
    private ProjectApi projectApi;

    public ApiListProjectReturn(ApiConnect ac){
        ApiListProjectReturn.ala = this;
        this.apico = ac;
        this.projectApi = ApiConnect.GLA.getProjectApi();
        this.lsp = ac.getProjectsList();
    }

    public void createAssignment(String name){
        try {
            this.projectApi.createProject(name);
            System.out.println("Your project "+name+" has been created");
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when creating project : The project "+name+" exists already." );
        }
    }

    public boolean deleteAssignment(int idProject){
        try {
            this.projectApi.deleteProject(idProject);
            System.out.println("Deletion Sucess");
            return true;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when deleting a project : "+idProject+" does not exist.");
        }

        return false;
    }

    public int getIdAssign(String name){
        for (int i = 0 ; i<this.lsp.size(); i++){
            if (this.lsp.get(i).getName().equals(name)){
                this.idAssign = this.lsp.get(i).getId();
                return this.lsp.get(i).getId();
            }
        }
        return -1;
    }

    public void show(){
        this.refresh();
        for (Project p: this.lsp) {
            System.out.println(p.getName());
        }
        System.out.println("");
    }

    public ProjectApi getProjectApi() {
        return projectApi;
    }

    public void refresh(){
        this.lsp = this.apico.getProjectsList();
    }

    public static ApiListProjectReturn getInstance() {
        return ApiListProjectReturn.ala;
    }
}
