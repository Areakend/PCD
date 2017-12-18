package eu.telecomnancy.pcd2k17.UI.java;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EnseignantScreenController implements Initializable{

	Stage prevStage;
	
	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);
	  
	  @FXML
	  public void handleClickConnect(ActionEvent event) throws IOException {
	    log.debug("Button was clicked!");
	    Stage stage = new Stage();
	    stage.setTitle("Shop Management");
	       Pane myPane = null;
	       myPane = FXMLLoader.load(getClass().getResource("createCategory.fxml"));
	       Scene scene = new Scene(myPane);
	       stage.setScene(scene);

	       prevStage.close();

	       stage.show();
	  }

	public void setPrevStage(Stage stage) {
		this.prevStage=stage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	  
	  
	  
}
