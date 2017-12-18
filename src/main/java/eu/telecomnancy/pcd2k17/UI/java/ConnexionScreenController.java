package eu.telecomnancy.pcd2k17.UI.java;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.telecomnancy.pcd2k17.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConnexionScreenController {

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);

	  public String Token;
	
	  @FXML
	  TextField tokenTextField = new TextField();
	  
	  @FXML
	  Label test;
	  
	  Parent appParent;
	  @FXML
	  public void handleClickConnect(ActionEvent event) throws IOException {
	    log.debug("Connexion button was clicked!");
	    log.debug("Input TextField : "+ tokenTextField.getText());	
	    log.debug(Main.class.toString());
	    log.debug(Main.class.getResource("EnseignantScreen.fxml"));
	    Parent root = FXMLLoader.load(Main.class.getResource("EnseignantScreen.fxml"));  
	    Stage app_stage = (Stage) (((javafx.scene.Node) event.getSource()).getScene().getWindow());
		Scene appScene = new Scene(root);
		
		//app_stage.hide();
		app_stage.setScene(new Scene(new Pane()));
		app_stage.show();
	  }
	  
	  
	  
}
