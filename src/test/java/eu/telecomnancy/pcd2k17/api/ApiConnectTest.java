package eu.telecomnancy.pcd2k17.api;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.*;

public class ApiConnectTest {
    @Test
    public void loginOK() throws Exception {
        ApiConnect ac = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
        ac.login("fail");

        assertFalse("Fail Login",ac.loginOK());

        ac.login("GUyKG2HZbHwzkVsss2pp\n");

        assertTrue("True login",ac.loginOK());
    }

    @Test
    public void setToken() throws Exception {
        ApiConnect ac = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
        ac.setToken("Test");
        String line ;
        BufferedReader in;

        try {
            File dir = new File(".token");
            dir.mkdir();
            in = new BufferedReader(new FileReader(".token/userToken.txt"));
            line = in.readLine();
            assertTrue("SetToken1",line.equals("Test"));
        }
        catch (Exception e){}

    }

}