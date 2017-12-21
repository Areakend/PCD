package eu.telecomnancy.pcd2k17;

import eu.telecomnancy.pcd2k17.api.ApiConnect;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
 
@SuppressWarnings("unused")
public class Main extends Application{
     
	final static Logger log = LogManager.getLogger(Main.class);

	
    public static BorderPane mainPane = new BorderPane();
    public static BorderPane panel0 = new BorderPane();
    public static BorderPane panel1 = new BorderPane();
    public static BorderPane panel2 = new BorderPane();
    public static BorderPane panel3 = new BorderPane();
	public static BorderPane panel4 = new BorderPane();
	public static BorderPane panel5 = new BorderPane();
	public static BorderPane panel6 = new BorderPane();
    
    public static Stage stage = new Stage();
    public static Stage stage2 = new Stage();
    
	public static ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");

	private static String Token = null;
	private static String commitMessage = null;
	public static LinkedList<String> fileList = new LinkedList<>();
	public static LinkedList<String> fileListPull = new LinkedList<>();

	private static int nbofFiles = 0;
	private static int connected = 0;
     
     
 
    @Override
    public void start(Stage stageX) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("ConnexionScreen.fxml"));

    	panel0 = FXMLLoader.load(getClass().getResource("ConnexionScreen.fxml"));
    	panel1 = FXMLLoader.load(getClass().getResource("EnseignantScreen.fxml"));
    	panel2 = FXMLLoader.load(getClass().getResource("CreateAssignement.fxml"));
    	panel3 = FXMLLoader.load(getClass().getResource("studentControl.fxml"));
		panel4 = FXMLLoader.load(getClass().getResource("GroupScreen.fxml"));
		panel5 = FXMLLoader.load(getClass().getResource("addStudent.fxml"));
		panel6 = FXMLLoader.load(getClass().getResource("pullScreen.fxml"));

		stage2.setScene(new Scene(root, 400, 200));
		stage2.show();

    }
     
    public static void main(String[] args) {
        launch(args);
    }
    
    public static String getToken() {
    	return Main.Token;
    }
    
    public static void setToken(String tok) {
    	Main.Token = tok;
    }
    
	public static String getCommitMessage() {
		return commitMessage;
	}

	public static void setCommitMessage(String commitMessage) {
		Main.commitMessage = commitMessage;
	}
	
	public static int getNbofFiles() {
		return nbofFiles;
	}

	public static void setNbofFiles(int nbofFiles) {
		Main.nbofFiles = nbofFiles;
	}

	public static int getConnected() {
		return connected;
	}

	public static void setConnected(int connected) {
		Main.connected = connected;
	}
}

