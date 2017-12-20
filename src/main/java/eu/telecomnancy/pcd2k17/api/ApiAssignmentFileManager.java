package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.TreeItem;

import java.io.File;
import java.util.List;

public class ApiAssignmentFileManager{
    private ApiProjectReturn assign;
    private List<Branch> listBranch;

    public ApiAssignmentFileManager(ApiProjectReturn assign_){
        this.assign = assign_;
    }

    public void showBranches(){
        try{
            this.listBranch = ApiConnect.REPOAPI.getBranches(this.assign.getIdAssign());
            for (Branch b:this.listBranch) {
                System.out.println(b.getName());
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't update the branches. \n Try to add a file or create a branch from master.");
        }
    }

    public void createBranch(String name, String source){
        try {
            ApiConnect.REPOAPI.createBranch(this.assign.getIdAssign(),name,source);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't create branch.");
        }
    }

    public void deleteBranch(String name){
        try {
            ApiConnect.REPOAPI.deleteBranch(this.assign.getIdAssign(),name);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't delete branch.");
        }
    }

    public Branch getBranch(String name){
        try{
            return ApiConnect.REPOAPI.getBranch(this.assign.getIdAssign(),name);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't find your branch.");
        }
        return null;
    }

    public void showElements(){
        List<TreeItem> tree = this.getElements();
        for (TreeItem t:tree) {
            System.out.println(t.getType().toString()+" - "+t.getName());
        }
    }

    public List<TreeItem> getElements(){
        try {
            return ApiConnect.REPOAPI.getTree(this.assign.getIdAssign());
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
            return ApiConnect.REPOFILEAPI.getRawFile(this.assign.getIdAssign(),branch,filepathRepository,dir);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't save the file. " + e);
        }

        return null;
    }

    public File saveFile(String branch,String filepathRepository){
        String nameRepo = this.assign.getName();
        return this.saveFile(branch,filepathRepository,".fileSave/"+nameRepo);
    }
}
