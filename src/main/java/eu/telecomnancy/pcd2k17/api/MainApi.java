package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GitLabApi;

public class MainApi {
    public static void main(String args[]) {
        // Log in to the GitLab server using a username and password
        apiConnect api = new apiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
        try {
            GitLabApi git = api.login();
            System.out.println("Login OK");
        }
        catch (Exception e){
            System.out.println("Erreur de login :'(");
        }


    }
}
