package eu.telecomnancy.pcd2k17.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApiListAssignmentTest {
    private ApiConnect api;
    private ApiListAssignment aa;
    private ApiAssignment assign1;

    @Before
    public void before(){
        api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
        api.login();
        //aa = new Api(api);
        //assign1 = new ApiAssignment("DevoirTest");
    }

    @Test
    public void createAssignment() throws Exception {
        //assign1.create();
        //assertTrue(api.getProjectApi().getProject(assign1.getIdAssign()).getName().equals("DevoirTest"));
    }

    @Test
    public void deleteAssignment() throws Exception {
    }

    @Test
    public void getIdAssign() throws Exception {
    }

    @Test
    public void refresh() throws Exception {
    }

}