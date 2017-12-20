package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Group;

import java.util.LinkedList;
import java.util.List;

public class ApiDiscipline extends ApiListDiscipline{
    private String name;
    private Group gp;

    public ApiDiscipline(String name_){
        super();
        this.name = name_;
        this.gp = this.getAssignment(name_);
        this.checkDiscipline();
    }

    private void checkDiscipline(){
        if(this.gp == null){
            this.createDiscipline(name);
            this.gp = this.getAssignment(this.name);
        }
    }

    public int getDisciplineId(){
        return this.gp.getId();
    }

    public void deleteDiscipline(){
        this.deleteDiscipline(this.getDisciplineId());
    }

    public String getName(){
        return this.name;
    }

    public List<String> getListAssignments(){
        List<String> list = new LinkedList<>();
        try{
            for (Group gp:ApiConnect.GROUP.getGroups()) {
                if ((gp.getFullName().startsWith(this.getName()))&&(gp.getFullName().contains("/"))){
                    list.add(gp.getName());
                }
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't show the disciplines. "+e);
        }
        return list;
    }

    public Group getAssignment(String name){
        try {
            for (Group gp: ApiConnect.GROUP.getGroups()) {
                if (gp.getName().equals(name)){
                    return gp;
                }
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("The Assignment doesn't exist. Creating a new one.");
        }
        return null;
    }

    public void showAssignments(){
        System.out.println("List of Assignments : ");
        int j=0;
        for (String s:this.getListAssignments()) {
            j++;
            System.out.println(s);
        }
        System.out.println("Total : "+j+"\n");
    }

}
