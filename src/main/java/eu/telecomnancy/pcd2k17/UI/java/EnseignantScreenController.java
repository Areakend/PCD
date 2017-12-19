package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnseignantScreenController {
	
	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);
	  
	  @FXML
	  public void goToCreateDevoir(ActionEvent event) throws IOException {
		    Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    FileLoaderStage.setScene(new Scene(Main.createDevoir, 1600, 800));
		    FileLoaderStage.show();
	  }

	
	  
	  
	  
}
