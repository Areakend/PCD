package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.User;

public class MainTestApi {
        public static void main(String args[]) {
            // Log in to the GitLab server using a username and password
            ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
            api.login();
            System.out.println("Login OK\n");

            ApiDiscipline Coucou = new ApiDiscipline("Coucou");
            api.showListDiscipline();

            ApiAssignment assign = new ApiAssignment(Coucou,"Devoir1","Test de devoir");
            ApiAssignment assign2 = new ApiAssignment(Coucou,"Devoir2","Test de devoir2");


            Coucou.showAssignments();


            api.showListDiscipline();

            ApiProjectReturn rendu1 = new ApiProjectReturn("Rendu de projet "+assign.getName(),assign);
            ApiProjectReturn rendu2 = new ApiProjectReturn("Rendu de projet "+assign2.getName(),assign2);

            assign.showProjects();

            ApiUser user = new ApiUser();
            for (User u: user.getUsers()) {
                System.out.println(u.getUsername() +" - " + u.getEmail() + " - " + u.getId());
            }
            System.out.println("Total : "+user.getListUserName().size());
        }

}
