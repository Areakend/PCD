package eu.telecomnancy.pcd2k17;

import org.apache.logging.log4j.Logger;

import java.io.File;


import org.apache.logging.log4j.LogManager;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainController {

  final static Logger log = LogManager.getLogger(MainController.class);

  @FXML
  public void handleClickOk(ActionEvent event) {
	  
		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Tanguy","*.csv"),
				new FileChooser.ExtensionFilter("Jerem","*.txt"),
				new FileChooser.ExtensionFilter("Julien","*.pdf")
				);

		File file = fileChooser.showOpenDialog(Main.primaryStage);
		Main.path = file.getAbsolutePath();
		log.debug("path : " + Main.path);
  }

}
