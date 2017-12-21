package eu.telecomnancy.pcd2k17.api;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
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

            //ApiProjectReturn rendu1 = new ApiProjectReturn("coucou","Rendu de projet "+assign.getName(),assign);
            //ApiProjectReturn rendu2 = new ApiProjectReturn("salut","Rendu de projet "+assign2.getName(),assign2);

            assign.showProjects();

            /*ApiFile file = new ApiFile(rendu1);
            try {
                file.pushFile("C:\\Users\\User\\Downloads\\Donkey_Kong.jpg","Gros Zeub");
            }
            catch (Exception e){
                System.out.println("Error : "+e);
            }*/
        }

}
