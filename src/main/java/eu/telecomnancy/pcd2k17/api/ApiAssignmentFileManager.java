package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.TreeItem;

import java.util.List;

public class ApiAssignmentFileManager{
    private ApiAssignment assign;
    private List<Branch> listBranch;

    public ApiAssignmentFileManager(ApiAssignment assign_){
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
        try {
            List<TreeItem> tree = ApiConnect.REPOAPI.getTree(this.assign.getIdAssign());
            for (TreeItem t:tree) {
                System.out.println(t.getType().toString()+" - "+t.getName());
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't show the repo's files. "+e);
        }
    }
}
