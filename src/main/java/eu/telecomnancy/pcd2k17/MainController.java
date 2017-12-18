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
		Stage app_stage = (Stage) (((javafx.scene.Node) event.getSource()).getScene().getWindow());
		File file = fileChooser.showOpenDialog(app_stage);
		if (file != null) {
			Main.path = file.getAbsolutePath();
		}
		log.debug("path : " + Main.path);
  }

}
