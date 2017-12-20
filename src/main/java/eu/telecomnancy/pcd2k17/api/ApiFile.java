package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.RepositoryFile;

import java.io.File;

public class ApiFile extends ApiAssignmentFileManager{

    public ApiFile(ApiProjectReturn project_){
        super(project_);
    }

    public RepositoryFile getFile(String path, String branch){
        try {
            return ApiConnect.REPOFILEAPI.getFile(path,this.project.getIdProject(),branch);
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't pull file. "+e);
        }
        return null;
    }
}
