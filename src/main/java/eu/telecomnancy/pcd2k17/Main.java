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
 
public class Main extends Application{
     
    public static BorderPane mainPane = new BorderPane();
    public static BorderPane panel0 = new BorderPane();
    public static BorderPane panel1 = new BorderPane();
    public static BorderPane panel2 = new BorderPane();
    public static BorderPane panel3 = new BorderPane();
	public static String Token = null;
	public static ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");
	public static String commitMessage = null;
	public static String fileList[] = new String[64];
	public static int nbofFiles = 0;
     
     
 
    @Override
    public void start(Stage stage) throws Exception {
       
    	panel0 = FXMLLoader.load(getClass().getResource("ConnexionScreen.fxml"));
    	panel1 = FXMLLoader.load(getClass().getResource("EnseignantScreen.fxml"));
    	panel2 = FXMLLoader.load(getClass().getResource("CreateAssignement.fxml"));
    	panel3 = FXMLLoader.load(getClass().getResource("studentControl.fxml"));

 
        mainPane.setCenter(panel0);
        Scene scene = new Scene(mainPane, 1400, 700);
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();
 
    }
     
    public static void main(String[] args) {
        launch(args);
    }
}
