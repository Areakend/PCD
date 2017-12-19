package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.telecomnancy.pcd2k17.Main;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateAssignementController{

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);
	private String matiere,titre,description,heureDebut,heureFin,path;
	private LocalDate dateDebut,dateFin;

	
	  ObservableList<String> list = FXCollections.observableArrayList("1","2");
	
	  @FXML
	  ChoiceBox matiereChoiceBox = new ChoiceBox();
	  
	  @FXML
	  TextField titreTextField = new TextField();
	  
	  @FXML
	  TextArea descriptionText = new TextArea();
	  
	  @FXML
	  DatePicker releaseDatePicker = new DatePicker();
	  
	  @FXML
	  TextField releaseHour = new TextField();
	  
	  @FXML
	  DatePicker endDatePicker = new DatePicker();
	  
	  @FXML
	  TextField endHour = new TextField();
	  
	  @FXML
	  Text pathText = new Text();
	  
	  @FXML
	  public void CreateDevoir(ActionEvent event) throws IOException {
		  /*Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    FileLoaderStage.setScene(new Scene(Main.createDevoir, 1600, 800));
		    FileLoaderStage.show();*/
		  log.debug("Create Devoir");
		  log.debug("Matiere : " +matiereChoiceBox.getValue());
		  log.debug("Titre : " + titreTextField.getText());
		  log.debug("Text : " + descriptionText.getText());
		  log.debug("ReleaseDate : " + releaseDatePicker.getValue() + " à " + releaseHour.getText()+"h");
		  log.debug("EndDate : " + endDatePicker.getValue() + " à " + endHour.getText()+"h");
		  
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
			path = file.getAbsolutePath();
			
			String doubleSlashPath = path.replace("\\", "\\\\");
			Path p1 = Paths.get(doubleSlashPath);	 
			pathText.setText(p1.getFileName().toString());
	  }
	  
	  @FXML
	  public void initialize(){
		  matiereChoiceBox.setItems(list);
		  matiereChoiceBox.setValue("1");  
		  descriptionText.setText("Hello");
	  }
	  
	  public boolean AllField(){
		  return true;
	  }

	
}
