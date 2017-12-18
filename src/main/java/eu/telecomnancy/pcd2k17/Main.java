package eu.telecomnancy.pcd2k17;

import org.apache.logging.log4j.Logger;

import eu.telecomnancy.pcd2k17.UI.java.ConnexionScreenController;
import eu.telecomnancy.pcd2k17.api.ApiConnect;

import org.apache.logging.log4j.LogManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

  final static Logger log = LogManager.getLogger(Main.class);
  static String path = null;
  public static String Token = null;
  public static Stage primaryStage;
  public static ApiConnect api = new ApiConnect("https://gitlab.telecomnancy.univ-lorraine.fr");

  
  public static void main(String args[]) {
    log.debug("executing main() method.");
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("JFX Sample Application");
        
    Parent root = FXMLLoader.load(getClass().getResource("ConnexionScreen.fxml"));

	//FXMLLoader loader = new FXMLLoader();
    //loader.setLocation(getClass().getResource("main.fxml"));
    //Parent root = loader.load();

    primaryStage.setOnCloseRequest(event -> {
      log.debug("terminating application.");
      Platform.exit();
    });
    primaryStage.setScene(new Scene(root, 400, 200));
    primaryStage.show();
  }

}
