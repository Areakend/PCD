package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.GitLabApi;

public class MainApi {
    public static void main(String args[]) {
        // Log in to the GitLab server using a username and password
        ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");

        GitLabApi git = api.login();
        System.out.println("Login OK\n");
        System.out.println("Existing Projects:");

        ApiListAssignment aa = new ApiListAssignment(api);
        System.out.println("");
        aa.show();

        ApiAssignment assign1 = new ApiAssignment("Devoir1");
        assign1.create();
        System.out.println("");
        aa.show();

        assign1.delete();
        System.out.println("");
        aa.show();


        //create assignment
        //aa.createAssigneent("Test2");

        //add Member
        //aa.addMembers(aa.getIdAssign("Test2") ,246 ,AccessLevel.DEVELOPER);
        //aa.delMembers(aa.getIdAssign("Test2"),246);

        //delete assignment
        //aa.deleteAssignment(aa.getIdAssign("Test2"));
    }
}
