package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Group;

public class ApiDiscipline extends ApiListDiscipline{
    private String name;
    private Group gp;

    public ApiDiscipline(String name_){
        super();
        this.name = name_;
        this.gp = this.getDiscipline(name_);
        this.checkDiscipline();
    }

    private void checkDiscipline(){
        if(this.gp == null){
            this.createDiscipline(name);
            this.gp = this.getDiscipline(this.name);
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


}
