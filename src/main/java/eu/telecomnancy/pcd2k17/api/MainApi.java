package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.AccessLevel;

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
        aa.setName("rs2017-hynes-roudaut");
        System.out.println("");

        //create assignement
        //aa.createAssignement("Test2");

        //add Member
        //aa.addMembers(aa.getIdAssign("Test2") ,246 ,AccessLevel.DEVELOPER);
        //aa.delMembers(aa.getIdAssign("Test2"),246);

        //delete assignement
        //aa.deleteAssignement(aa.getIdAssign("Test2"));
    }
}
