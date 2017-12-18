package eu.telecomnancy.pcd2k17.api;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.Project;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ApiConnect {
    private String url;
    private List<Project> projects;
    private GitLabApi GLA;

    public ApiConnect(String u){
        this.url = u;
    }

    public GitLabApi login(){
        this.getToken();
        this.GLA = new GitLabApi(this.url, this.getToken());
        return this.GLA;
    }

    public GitLabApi login(String tok){
        this.setToken(tok);
        return this.login();
    }

    public boolean loginOK(){
        try{
            if (this.GLA.getProjectApi().getProjects().size() >0){
                return true;
            }
        }
        catch (Exception e){

        }
        File file = new File(".token/userToken.txt");
        file.delete();
        return false;
    }

    public List<Project> getProjectsList(GitLabApi git){
        try {
            projects = git.getProjectApi().getMemberProjects();
            return this.projects;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println(e);
        }
        return null;
    }

    public ProjectApi getProjectApi(){
        return this.GLA.getProjectApi();
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
        catch (Exception e){
//            try {
//                Scanner reader = new Scanner(System.in);
//                System.out.println("Token :");
//                this.setToken(reader.nextLine());
//                reader.close();
//            }
//            catch (Exception ee){}
        }
        return null;
    }

    public void setToken(String tok){
        try {
            PrintWriter writer = new PrintWriter(".token/userToken.txt", "UTF-8");
            writer.println(tok);
            writer.close();
        }
        catch (Exception ee){}
    }
}