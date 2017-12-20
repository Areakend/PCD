package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GroupApi;

public abstract class ApiListDiscipline {
    private GroupApi group;

    protected ApiListDiscipline(){
        this.group = ApiConnect.GROUP;
    }

    public void createDiscipline(String name){
        try{
            this.group.addGroup(name,name);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't create the discipline. "+e);
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
