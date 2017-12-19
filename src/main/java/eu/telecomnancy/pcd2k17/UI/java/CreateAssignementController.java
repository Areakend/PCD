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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateAssignementController {

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);
	  
	  @FXML
	  public void CreateDevoir(ActionEvent event) throws IOException {
		  /*Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    FileLoaderStage.setScene(new Scene(Main.createDevoir, 1600, 800));
		    FileLoaderStage.show();*/
		  log.debug("Create Devoir");
	  }
	  
	  @FXML
	  public void importCSV(ActionEvent event) {
		  
			final FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Tanguy","*.csv"),
					new FileChooser.ExtensionFilter("Jerem","*.pd"),
					new FileChooser.ExtensionFilter("Julien","*.jpeg2000"),
					new FileChooser.ExtensionFilter("Raph","*.*")
					);
			
			Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			File file = fileChooser.showOpenDialog(FileLoaderStage);
			Main.path = file.getAbsolutePath();
			log.debug("path : " + Main.path);
	  }
}
