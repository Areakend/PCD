package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.Group;


public abstract class ApiListAssignment {
    private GroupApi group;
    private ApiDiscipline discipline;

    public ApiListAssignment(ApiDiscipline discipline_){
        this.group = ApiConnect.GROUP;
        this.discipline = discipline_;
    }

    public void createAssignment(String name){
        try{
            this.group.addGroup(name,this.discipline.getName()+"."+name);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't create a new assignment. " + e);
        }
    }

    protected void deleteAssignment(int idAssignment){
        try{
            this.group.deleteGroup(idAssignment);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't delete assignment. "+e);
        }
    }

    protected Group getAssignment(String path){
        try {
            return this.group.getGroup(path);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("The Assignment doesn't exist. Creating a new one.");
        }
        return null;
    }

}
