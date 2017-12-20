package eu.telecomnancy.pcd2k17.api;


import org.gitlab4j.api.models.Group;

public class ApiAssignment extends ApiListAssignment{
    private String name;
    private String path;
    private Group gp;
    private ApiListAssignment listAssignment;

    public ApiAssignment(String name_, ApiDiscipline discipline){
        super(discipline);
        this.name = name_;
        this.gp = this.getAssignment(name);
        this.checkAssignment();
    }

    private void checkAssignment(){
        if(this.gp == null){
            this.createAssignment(name);
            this.gp = this.getAssignment(this.name);
        }
    }

    public int getAssignmentId(){
        return this.gp.getId();
    }

    public void deleteAssignment(){
        this.deleteAssignment(this.getAssignmentId());
    }

    public String getName(){
        return this.name;
    }

}
