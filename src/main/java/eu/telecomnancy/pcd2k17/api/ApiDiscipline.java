package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Visibility;

import java.util.LinkedList;
import java.util.List;

public class ApiDiscipline extends ApiListDiscipline{
    private String name;
    private Group gp;
    private Visibility vis;

    public ApiDiscipline(String name_,Visibility vis_)throws Exception{
        super();
        this.name = name_;
        this.vis = vis_;
        this.gp = ApiConnect.getInstance().getDiscipline(name_);
        this.checkDiscipline();
    }

    public ApiDiscipline(String name_)throws Exception{
        super();
        this.name = name_;
        this.vis = Visibility.PRIVATE;
        this.gp = ApiConnect.getInstance().getDiscipline(name_);
        this.checkDiscipline();
    }

    private void checkDiscipline()throws Exception{
        if(this.gp == null){
            this.createDiscipline(name,vis);
            this.gp = ApiConnect.getInstance().getDiscipline(this.name);
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

    public Visibility getVisibility(){
        return this.vis;
    }

}
