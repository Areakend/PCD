package eu.telecomnancy.pcd2k17.UI.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gitlab4j.api.GitLabApi;

import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.api.ApiConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConnexionScreenController {

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);

	@FXML
	TextField tokenTextField = new TextField();

	@FXML
	Label test;

	@FXML
	public void handleClickConnect(ActionEvent event) {
		log.debug("Connexion button was clicked!");
		log.debug("Input TextField : " + tokenTextField.getText());

		Main.Token = tokenTextField.getText();
		Main.api.login(Main.Token);
		if (Main.api.loginOK()) {
			System.out.println("Connection réussie"); //Afficher stage suivant
		}

	}
}
