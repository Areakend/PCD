package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;

import java.util.LinkedList;
import java.util.List;

public class ApiAssignment extends ApiListAssignment{
    private String name;
    private String desc;
    private ApiDiscipline discipline;
    private Group gp;
    private ApiListAssignment listAssignment;

    public ApiAssignment(ApiDiscipline discipline_,String name_, String desc_){
        super(discipline_);
        this.name = name_;
        this.desc = desc_;
        this.discipline = discipline_;
        this.gp = this.discipline.getAssignment(name);
        this.checkAssignment();
    }

    public ApiAssignment (ApiDiscipline discipline_,String name_){
        super(discipline_);
        this.name = name_;
        this.desc = "";
        this.discipline = discipline_;
        this.gp = this.discipline.getAssignment(name);
        this.checkAssignment();
    }

    private void checkAssignment(){
        if(this.gp == null){
            this.createAssignment(name,desc);
            this.gp = this.discipline.getAssignment(this.name);
        }
    }

    public int getAssignmentId(){
        return this.gp.getId();
    }

    public void deleteAssignment(){
        this.deleteAssignment(this.getAssignmentId());
    }

    public List<Project> getListProjects(){
        try{
            List<Project> listProject = new LinkedList<>();
            for (Project p:ApiConnect.PROJECT.getMemberProjects()) {
                if (p.getPathWithNamespace().contains(this.getName())){
                    listProject.add(p);
                }
            }
            return listProject;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get the projects. "+e);
        }
        return null;
    }

    public Project getProject(String name){
        for (Project p: this.getListProjects()) {
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public void showProjects(){
        System.out.println("List of projects  of " + this.getName() + ":");
        int i = 0;
        for (Project p: this.getListProjects()) {
            System.out.println(p.getName());
            i++;
        }
        System.out.println("Total : "+i+"\n");
    }

    public String getName(){
        return this.name;
    }

}
