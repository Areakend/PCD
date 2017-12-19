package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.RepositoryFile;

import java.io.File;

public class ApiFile{
    private ApiAssignment assign;

    public ApiFile(ApiAssignment assign_){
        this.assign = assign_;
    }

    public RepositoryFile createFile(String name, String path, String branch){
        RepositoryFile repoFile = new RepositoryFile();
        repoFile.setFileName(name);
        repoFile.setFilePath(path);
        repoFile.setBlobId("BlobId");
        repoFile.setCommitId("CommitId");
        repoFile.setContent("Content");
        repoFile.setEncoding("UTF8");
        repoFile.setLastCommitId("LastCommitId");
        repoFile.setSize(100);
        repoFile.setRef(branch);
        return repoFile;
    }

    public void pushFile(RepositoryFile file,String branch, String commit){
        try {
            ApiConnect.REPOFILEAPI.createFile(file,this.assign.getIdAssign(),branch,commit);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't push file. "+e);
        }
    }

    public RepositoryFile getFile(String path, String branch){
        try {
            return ApiConnect.REPOFILEAPI.getFile(path,this.assign.getIdAssign(),branch);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't pull file. "+e);
        }
        return null;
    }

    public File saveFile(String branch,String filepathRepository,String directoryPath){
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
        return this.saveFile(branch,filepathRepository,".saveFile");
    }


}
