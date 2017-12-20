package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.Group;

import java.util.LinkedList;
import java.util.List;

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

    protected Group getDiscipline(String name){
        try {
            List<Group> group = this.group.getGroups();
            for (int i = 0 ; i<group.size();i++){
                if (group.get(i).getName().equals(name)){
                    return group.get(i);
                }
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get groups. "+e );
        }
        return null;
    }

    protected List<String> getListDiscipline(){
        List<String> list = new LinkedList<>();
        try{
            for (Group gp:this.group.getGroups()) {
                list.add(gp.getName());
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't show the disciplines. "+e);
        }
        return list;
    }

    public void showListDiscipline(){
        try{
            System.out.println("\n List of disciplines :");
            for (Group gp:this.group.getGroups()) {
                System.out.println(gp.getName());
            }
            System.out.println("Total : "+this.group.getGroups().size());
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't show the disciplines. "+e);
        }

    }
}
