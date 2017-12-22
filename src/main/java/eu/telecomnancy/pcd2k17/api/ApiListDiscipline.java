package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.Visibility;


public abstract class ApiListDiscipline {
    private GroupApi group;

    protected ApiListDiscipline(){
        this.group = ApiConnect.GROUP;
    }

    public void createDiscipline(String name, Visibility vis)throws Exception{
        try{
            this.group.addGroup(name,name,"",Boolean.FALSE,Boolean.TRUE,vis,Boolean.FALSE,Boolean.FALSE,null,0);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            throw new Exception("Can't create new discipline.");
        }
    }

    protected void deleteDiscipline(int groupId){
        try{
            this.group.deleteGroup(groupId);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't delete discipline. "+e);
        }
    }
}
