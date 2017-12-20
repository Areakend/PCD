package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.ProjectApi;

public abstract class ApiListProjectReturn {
    private ProjectApi projectApi;

    protected ApiListProjectReturn(){
        this.projectApi = ApiConnect.GLA.getProjectApi();
    }

    protected void createProject(String name, int groupId){
        try {
            this.projectApi.createProject(groupId,name);
            System.out.println("Your project "+name+" has been created");
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Error when creating project : The project "+name+" exists already." );
        }
    }

    protected boolean deleteProject(int idProject){
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

}
