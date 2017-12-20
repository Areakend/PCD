package eu.telecomnancy.pcd2k17.UI.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnexionScreenController {

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);
	// public static Stage FileLoaderStage;

	@FXML
	TextField tokenTextField = new TextField();

	@FXML
	public void handleClickConnect(ActionEvent event) throws Exception {
		log.debug("Connexion button was clicked!");
		log.debug("Input TextField : " + tokenTextField.getText());

		Main.Token = tokenTextField.getText();
		if (Main.Token.length() > 0) {
			Main.api.login(Main.Token);
			if (Main.api.loginOK()) {
				log.debug("Connection reussie"); // Afficher stage suivant

				Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				FileLoaderStage.hide();
				FileLoaderStage.setScene(new Scene(Main.rootEnseignant, 1600, 800));
				FileLoaderStage.show();
			}
			else {Alert connectionError = new Alert(AlertType.ERROR);
			connectionError.setTitle("Connection error");
			connectionError.setHeaderText(null);
			connectionError.setContentText("Could not connect with this token");
			connectionError.showAndWait();}
		}

	}

}
