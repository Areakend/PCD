package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.TreeItem;

import java.io.File;
import java.util.List;

public abstract class ApiAssignmentFileManager{
    protected ApiProjectReturn project;

    public ApiAssignmentFileManager(ApiProjectReturn project_){
        this.project = project_;
    }

    public void showElements(){
        List<TreeItem> tree = this.getElements();
        for (TreeItem t:tree) {
            System.out.println(t.getType().toString()+" - "+t.getName());
        }
    }

    public List<TreeItem> getElements(){
        try {
            return ApiConnect.REPOAPI.getTree(this.project.getIdProject());
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get the elements from repo. "+e);
        }
        return null;
    }

    public File saveFile(String branch, String filepathRepository, String directoryPath){
        File dir = new File(directoryPath);
        dir.mkdir();
        try{
            return ApiConnect.REPOFILEAPI.getRawFile(this.project.getIdProject(),branch,filepathRepository,dir);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't save the file. " + e);
        }

        return null;
    }

    public File saveFile(String branch,String filepathRepository){
        String nameRepo = this.project.getName();
        return this.saveFile(branch,filepathRepository,".fileSave/"+nameRepo);
    }
}
