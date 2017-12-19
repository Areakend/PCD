package eu.telecomnancy.pcd2k17.api;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.RepositoryApi;
import org.gitlab4j.api.RepositoryFileApi;
import org.gitlab4j.api.models.Project;

import java.io.*;
import java.util.List;

public final class ApiConnect {
    public static GitLabApi GLA;
    public static RepositoryApi REPOAPI;
    public static RepositoryFileApi REPOFILEAPI;

    private String url;
    private static ApiConnect apico;

    public ApiConnect(String u){
        ApiConnect.apico = this;
        this.url = u;
    }

    public void login(){
        this.getToken();
        ApiConnect.GLA = new GitLabApi(this.url, this.getToken());
        ApiConnect.REPOAPI = new RepositoryApi(ApiConnect.GLA);
        ApiConnect.REPOFILEAPI = new RepositoryFileApi(ApiConnect.GLA);
    }

    public void login(String tok){
        this.setToken(tok);
        this.login();
    }

    public boolean loginOK(){
        try{
            if (ApiConnect.GLA.getProjectApi().getProjects().size() >0){
                return true;
            }
        }
        catch (Exception e){
        }
        File file = new File(".token/userToken.txt");
        file.delete();
        return false;
    }

    public List<Project> getProjectsList(){
        try {
            return ApiConnect.GLA.getProjectApi().getMemberProjects();
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println(e);
        }
        return null;    }

    public ProjectApi getProjectApi(){
        return ApiConnect.GLA.getProjectApi();
    }

	@SuppressWarnings("resource")
	private String getToken(){
        String line ;
        BufferedReader in;

        try {
            File dir = new File(".token");
            dir.mkdir();
            in = new BufferedReader(new FileReader(".token/userToken.txt"));
            line = in.readLine();
            return line;
        }
        catch (Exception e){}
        return null;
    }

    public void setToken(String tok){
        try {
            PrintWriter writer = new PrintWriter(".token/userToken.txt", "UTF-8");
            writer.println(tok);
            writer.close();
        }
        catch (Exception ee){System.out.println("marche pas");}
    }

    public static ApiConnect getInstance(){
        return ApiConnect.apico;
    }

}