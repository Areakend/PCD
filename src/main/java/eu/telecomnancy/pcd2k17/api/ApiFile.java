package eu.telecomnancy.pcd2k17.api;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ApiFile extends ApiAssignmentFileManager{

    public ApiFile(ApiProjectReturn project_){
        super(project_);
    }

    public RepositoryFile getFile(String path){
        try {
            return ApiConnect.REPOFILEAPI.getFile(path,this.project.getIdProject(),"master");
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't pull file. "+e);
        }
        return null;
    }

    public void pushFile(String pathFile, String commit) throws Exception{
        File localPath = File.createTempFile(this.project.getName(), "");
        User me = ApiConnect.USER.getCurrentUser();
        if(!localPath.delete()) {
            throw new IOException("Could not delete temporary file " +
                    localPath);
        }

        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(me.getUsername(),this.getToken());
        String url = this.project.getProject().getHttpUrlToRepo();

        //git clone
        Git git = Git.cloneRepository()
                .setURI(url)
                .setDirectory(localPath)
                .setCredentialsProvider(cp)
                .call();

        File file = new File(pathFile);
        FileUtils.copyFileToDirectory(file,localPath);

        //git add
        git.add().addFilepattern(file.getName()).call();

        //git commit
        git.commit().setMessage(commit).call();

        //git push
        git.push() .setCredentialsProvider(cp) .call();
    }

    private String getToken() throws Exception{
        String line ;
        BufferedReader in;
        File dir = new File(".token");
        dir.mkdir();
        in = new BufferedReader(new FileReader(".token/userToken.txt"));
        line = in.readLine();
        return line;
    }

}
