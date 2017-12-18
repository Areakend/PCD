package eu.telecomnancy.pcd2k17.UI.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConnexionScreenController {

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);

	  public String Token;
	
	  @FXML
	  TextField tokenTextField = new TextField();
	  
	  @FXML
	  Label test;
	  
	  @FXML
	  public void handleClickConnect(ActionEvent event) {
	    log.debug("Connexion button was clicked!");
	    log.debug("Input TextField : "+ tokenTextField.getText());
	    
	  }
	  
	  
	  
}
