package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.Visibility;

import java.security.spec.ECField;

public abstract class ApiListAssignment {
    protected GroupApi group;
    private ApiDiscipline discipline;

    public ApiListAssignment(ApiDiscipline discipline_){
        this.group = ApiConnect.GROUP;
        this.discipline = discipline_;
    }

    public void createAssignment(String name,String desc)throws Exception{
        try{
            this.group.addGroup(name,name,desc,Boolean.FALSE,Boolean.TRUE,this.discipline.getVisibility(),Boolean.FALSE,Boolean.FALSE,this.discipline.getDisciplineId(),0);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            throw new Exception("Can't create new assignment.");
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
