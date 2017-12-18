package eu.telecomnancy.pcd2k17;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

  final static Logger log = LogManager.getLogger(Main.class);

  public static void main(String args[]) {
    log.debug("executing main() method.");
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("JFX Sample Application");

    Parent root = FXMLLoader.load(getClass().getResource("ConnexionScreen.fxml"));  

    primaryStage.setOnCloseRequest(event -> {
      log.debug("terminating application.");
      Platform.exit();
    });
    primaryStage.setScene(new Scene(root, 400, 200));
    primaryStage.show();
  }

}