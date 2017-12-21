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
    
    public static Stage stage = new Stage();
    public static Stage stage2 = new Stage();

	public static String Token = null;
	public static ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
	public static String commitMessage = null;
	public static String fileList[] = new String[64];
	public static int nbofFiles = 0;
	public static int connected = 0;
     
     
 
    @Override
    public void start(Stage stageX) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("ConnexionScreen.fxml"));

    	panel0 = FXMLLoader.load(getClass().getResource("ConnexionScreen.fxml"));
    	panel1 = FXMLLoader.load(getClass().getResource("EnseignantScreen.fxml"));
    	panel2 = FXMLLoader.load(getClass().getResource("CreateAssignement.fxml"));
    	panel3 = FXMLLoader.load(getClass().getResource("studentControl.fxml"));
		panel4 = FXMLLoader.load(getClass().getResource("GroupScreen.fxml"));
		panel5 = FXMLLoader.load(getClass().getResource("addStudent.fxml"));

		stage2.setScene(new Scene(root, 400, 200));
		stage2.show();

    }
     
    public static void main(String[] args) {
        launch(args);
    }
}
