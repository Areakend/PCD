package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GitLabApi;

public class MainApi {
    public static void main(String args[]) {
        // Log in to the GitLab server using a username and password
        ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");

        GitLabApi git = api.login();
        System.out.println("Login OK\n");
        System.out.println("Existing Projects:");
        for (int i =0 ; i<api.getProjectsList(git).size() ; i++)
            System.out.println(api.getProjectsList(git).get(i).getName());

        System.out.println("");
        ApiAssignement aa = new ApiAssignement(api);
        aa.setName("rs");
        aa.checkAssignement();
        aa.setName("rs2017-hynes-roudaut");
        aa.checkAssignement();
        System.out.println("");

        //create assignement
        //aa.createAssignement("Test4");
        
        //delete assignement
        //aa.deleteAssignement(aa.getIdAssign("Test2"));

    }
}
