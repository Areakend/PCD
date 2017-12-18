package eu.telecomnancy.pcd2k17.api;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class apiConnect {
    private String url;
    private List<Project> projects;

    public apiConnect(String u){
        this.url = u;
    }

    public GitLabApi login(String token){
        GitLabApi git = new GitLabApi(this.url, token);
        return git;
    }

    public List<Project> getProjects(GitLabApi git) throws org.gitlab4j.api.GitLabApiException{
        projects = git.getProjectApi().getProjects();
        for (int i =0 ; i<projects.size() ; i++){
            System.out.println(projects.get(i).getName());
        }
        return this.projects;
    }

    private String getToken(){
        String line;
        BufferedReader in;

        try {
            File dir = new File(".token");
            dir.mkdir();
            in = new BufferedReader(new FileReader(".token/userToken.txt"));
            line = in.readLine();
            if (line != null) System.out.println(line);
            else throw new IOException();
        }
        catch (java.io.FileNotFoundException e){
            System.out.println("Fichier inexistant\n");
            try {
                Scanner reader = new Scanner(System.in);
                System.out.println("Token :");
                PrintWriter writer = new PrintWriter(".token/userToken.txt", "UTF-8");
                writer.println(reader.nextLine());
                writer.close();
                reader.close();
            }
            catch (Exception ee){

            }
        }
        catch (java.io.IOException ioe){
            System.out.println("Fichier Vide\n");
        }

        return "zizi";
    }

    public static void main(String args[]) {
        // Log in to the GitLab server using a username and password
        apiConnect api = new apiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
        System.out.println(api.getToken());
        /*
        try {
            GitLabApi git = api.login("1pNC2G7U7UvXs7_4M9zG");
        }
        catch (Exception e){
            System.out.println("Erreur de login :'(");
        }
         */

    }
}
