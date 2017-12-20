package eu.telecomnancy.pcd2k17.api;

public class MainTestApi {
        public static void main(String args[]) {
            // Log in to the GitLab server using a username and password
            ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
            api.login();
            System.out.println("Login OK\n");

            ApiDiscipline Coucou = new ApiDiscipline("Coucou");
            Coucou.showListDiscipline();

            ApiAssignment assign = new ApiAssignment("Devoir1",Coucou,"Test de devoir");
            ApiAssignment assign2 = new ApiAssignment("Devoir2",Coucou,"Test de devoir2");


            assign.showAssignments();


            Coucou.showListDiscipline();

            //assign.deleteAssignment();

        }

}
