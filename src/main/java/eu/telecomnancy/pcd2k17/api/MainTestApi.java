package eu.telecomnancy.pcd2k17.api;

public class MainTestApi {
        public static void main(String args[]) {
            // Log in to the GitLab server using a username and password
            ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
            api.login();
            System.out.println("Login OK\n");

            ApiDiscipline Coucou = new ApiDiscipline("Coucou");
            Coucou.showListDiscipline();

            //ApiAssignment assign = new ApiAssignment("Devoir1",Coucou);

            System.out.println("");
            ApiDiscipline Test = new ApiDiscipline("Test");
            System.out.println(Test.getDiscipline(Test.getName()).getFullPath());


            Coucou.showListDiscipline();
            //assign.deleteAssignment();

        }

}
