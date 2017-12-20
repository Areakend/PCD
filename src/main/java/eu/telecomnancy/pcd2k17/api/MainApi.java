package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.models.RepositoryFile;

import java.io.File;

public class MainApi {
    public static void main(String args[]) {
        // Log in to the GitLab server using a username and password
        ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
        api.login();
        System.out.println("Login OK\n");
        System.out.println("Existing Projects:");

        ApiListProjectReturn aa = new ApiListProjectReturn(api);

        //aa.show();
        ApiProjectReturn assign1 = new ApiProjectReturn("Devoir");
        //assign1.create();
        //System.out.println("");
        aa.show();

        //assign1.addMembers(assign1.getIdAssign(),246);

        //assign1.delete();
        //System.out.println("");
        //aa.show();
        //assign1.setDescription("Bonjour à tous ! :)");
        //System.out.println(assign1.getDescription());

        ApiAssignmentFileManager fileManager = new ApiAssignmentFileManager(assign1);
        ApiFile file = new ApiFile(assign1);

        System.out.println("");
        fileManager.showElements();
        System.out.println("");
        String path = ".fileSave";
        String path2 = "https://gitlab.telecomnancy.univ-lorraine.fr/Jeremy.Hynes/devoir.git";
        String branch = "master";

        //RepositoryFile rpFile2 = file.getFile("Test_a_dl",branch);

        //System.out.println(rpFile2.getFilePath());

        //File f = fileManager.saveFile(branch,"Test_a_dl");
        assign1.getLocalAssignment(fileManager);
        //fileManager.createBranch("Test2","master");
        //fileManager.showBranches();
        //fileManager.deleteBranch("Test");
        //fileManager.deleteBranch("Test2");
        //fileManager.showBranches();
    }
}
