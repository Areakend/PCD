package eu.telecomnancy.pcd2k17.api;


import org.gitlab4j.api.models.Group;

import java.util.LinkedList;
import java.util.List;

public class ApiAssignment extends ApiListAssignment{
    private String name;
    private String desc;
    private ApiDiscipline discipline;
    private Group gp;
    private ApiListAssignment listAssignment;

    public ApiAssignment(String name_, ApiDiscipline discipline_, String desc_){
        super(discipline_);
        this.name = name_;
        this.desc = desc_;
        this.discipline = discipline_;
        this.gp = this.getAssignment(name);
        this.checkAssignment();
    }

    private void checkAssignment(){
        if(this.gp == null){
            this.createAssignment(name,desc);
            this.gp = this.getAssignment(this.name);
        }
    }

    public int getAssignmentId(){
        return this.gp.getId();
    }

    public void deleteAssignment(){
        this.deleteAssignment(this.getAssignmentId());
    }

    protected List<String> getListAssignments(){
        List<String> list = new LinkedList<>();
        try{
            for (Group gp:this.group.getGroups()) {
                if (gp.getId() == this.gp.getParentId())
                    list.add(gp.getName());
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't show the disciplines. "+e);
        }
        return list;
    }

    public void showAssignments(){
        try {
            int j = 0;
            System.out.println("List of Assignments : ");
            List<Group> group = this.group.getGroups();

            System.out.println(this.gp.getParentId() + " + " + this.gp.getName());

            for (int i = 0 ; i<group.size();i++){

                System.out.println(group.get(i).getId()+" - " +group.get(i).getFullName() + " - " + group.get(i).getFullName().startsWith(this.discipline.getName()));


                if((group.get(i).getFullName().startsWith(this.discipline.getName())
                        && (this.gp.getParentId()!=group.get(i).getId()))){
                    j++;
                    System.out.println(group.get(i).getName());
                }
            }
            System.out.println("Total : "+j+"\n");
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("The Assignment doesn't exist. Creating a new one.");
        }
    }

    public String getName(){
        return this.name;
    }

}
