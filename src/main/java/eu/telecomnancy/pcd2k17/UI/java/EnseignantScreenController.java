package eu.telecomnancy.pcd2k17.UI.java;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EnseignantScreenController {

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);

	@FXML
	public void goToCreateDevoir(ActionEvent event) throws IOException {

		Main.mainPane.setCenter(Main.panel2);

	}


	@FXML
	public void goToConsultDevoir(ActionEvent event) throws IOException {

		Main.mainPane.setCenter(Main.panel3);

		
	}

}
