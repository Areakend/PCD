package eu.telecomnancy.pcd2k17.api;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Project;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ApiConnect {
    private String url;
    private List<Project> projects;

    public ApiConnect(String u){
        this.url = u;
    }

    public GitLabApi login(){
        this.getToken();
        GitLabApi git = new GitLabApi(this.url, this.getToken());
        return git;
    }

    public boolean loginOK(GitLabApi git) throws org.gitlab4j.api.GitLabApiException{
        if (git.getProjectApi().getProjects().size() >0){
            return true;
        }
        return false;
    }

    public List<Project> getProjects(GitLabApi git) throws org.gitlab4j.api.GitLabApiException{
        projects = git.getProjectApi().getProjects();
        for (int i =0 ; i<projects.size() ; i++){
            System.out.println(projects.get(i).getName());
        }
        return this.projects;
    }

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
            try {
                Scanner reader = new Scanner(System.in);
                System.out.println("Token :");
                PrintWriter writer = new PrintWriter(".token/userToken.txt", "UTF-8");
                writer.println(reader.nextLine());
                writer.close();
                reader.close();
            }
            catch (Exception ee){}
        }
        return null;
    }
}
