package eu.telecomnancy.pcd2k17.UI.java;

import java.io.IOException;
import javafx.scene.text.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.api.ApiConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class EnseignantScreenController {

	@FXML
	
	Text nameText = new Text();

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);

	@FXML
	public void goToCreateDevoir(ActionEvent event) throws IOException {
		nameText.setText(Main.api.getCurrentUserName());
		Main.mainPane.setCenter(Main.panel2);

	}

	@FXML
	public void goToConsultDevoir(ActionEvent event) throws IOException {
		nameText.setText(Main.api.getCurrentUserName());
		Main.mainPane.setCenter(Main.panel3);
	}
	
	@FXML
	public void goToPullDevoir(ActionEvent event) throws IOException {
		nameText.setText(Main.api.getCurrentUserName());
		Main.mainPane.setCenter(Main.panel6);
	}

	@FXML
	public void Disconnect(ActionEvent event) throws IOException {
		Main.setToken("Disconnect");
		Main.setConnected(2);
		nameText.setText(" ");
		Main.api.login(Main.getToken());
		Main.mainPane.setCenter(Main.panel3);
		Main.stage.hide();
		Main.stage2.show();

	}

}
