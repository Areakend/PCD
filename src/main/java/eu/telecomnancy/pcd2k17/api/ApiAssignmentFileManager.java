package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.TreeItem;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public abstract class ApiAssignmentFileManager{
    protected ApiProjectReturn project;

    public ApiAssignmentFileManager(ApiProjectReturn project_){
        this.project = project_;
    }

    public List<String> getElements(){
        try {
            List<String> list = new LinkedList<>();
            for (TreeItem t:ApiConnect.REPOAPI.getTree(this.project.getIdProject())) {
                list.add(t.getName());
            }
            return list;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get the elements from repo. "+e);
        }
        return null;
    }

    public File saveFile(String filepathRepository, String directoryPath){
        File dir = new File(directoryPath);
        dir.mkdir();
        try{
            return ApiConnect.REPOFILEAPI.getRawFile(this.project.getIdProject(),"master",filepathRepository,dir);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't save the file. " + e);
        }

        return null;
    }

    public File saveFile(String filepathRepository){
        String nameRepo = this.project.getName();
        return this.saveFile(filepathRepository,".fileSave/"+nameRepo);
    }
}
