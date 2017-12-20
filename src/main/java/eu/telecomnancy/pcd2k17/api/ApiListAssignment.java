package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.Visibility;

public abstract class ApiListAssignment {
    protected GroupApi group;
    private ApiDiscipline discipline;

    public ApiListAssignment(ApiDiscipline discipline_){
        this.group = ApiConnect.GROUP;
        this.discipline = discipline_;
    }

    public void createAssignment(String name,String desc){
        try{
            this.group.addGroup(name,name,desc,Boolean.FALSE,Boolean.TRUE,Visibility.PRIVATE,Boolean.FALSE,Boolean.FALSE,this.discipline.getDisciplineId(),0);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't create a new assignment. " + e);
        }
    }

    public void deleteAssignment(int idAssignment){
        try{
            this.group.deleteGroup(idAssignment);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't delete assignment. "+e);
        }
    }
}
