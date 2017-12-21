package eu.telecomnancy.pcd2k17.UI.java;


import java.io.IOException;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;


public class CreateAssignementController{

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);
	private String discipline,title,description,heureFin,isPrivate;

	private LocalDate releaseDate,deadline;
	
	private int NbEleves;
	
	  ObservableList<String> list = FXCollections.observableArrayList("1","2");
	
	  @SuppressWarnings("rawtypes")
	@FXML
	  ChoiceBox matiereChoiceBox = new ChoiceBox();
	  
	  @FXML
	  TextField titreTextField = new TextField();
	  
	  @FXML
	  TextField prefixTextField = new TextField();
	  
	  @FXML
	  TextArea descriptionText = new TextArea();
	  
	  @FXML
	  DatePicker releaseDatePicker = new DatePicker();
	  
	  @FXML
	  DatePicker endDatePicker = new DatePicker();
	  
	  @FXML
	  TextField endHour = new TextField();
	  
	  @FXML
	  RadioButton radioPrivate; 
	  
	  @FXML
	  RadioButton radioPublic;  
	  
	  @FXML
	  TextField NbElevesTextField = new TextField();
	 
	  @FXML
	  public void CreateDevoir(ActionEvent event) throws IOException {
		  /*Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    FileLoaderStage.setScene(new Scene(Main.createDevoir, 1600, 800));
		    FileLoaderStage.show();*/
		  boolean error = false;
		  if ( titreTextField.getText().length() == 0 || prefixTextField.getText().length() == 0||descriptionText.getText().length() == 0 
				  || releaseDatePicker.getValue() == null || endDatePicker.getValue() == null || endHour.getText().length() == 0
				  ||NbElevesTextField.getText().length() == 0){
			  Alert alert = new Alert(AlertType.WARNING);
		        alert.setTitle("Formulaire(s) vide(s)");
		        alert.setHeaderText("Formulaire(s) vide(s).");
		        alert.setContentText("Veuillez remplir tous les formulaires.");

		        alert.showAndWait();
		  } else if(!endHour.getText().matches("\\d{1,2}:\\d{1,2}")) {
			  Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Erreur de format");
		        alert.setHeaderText("Erreur de format");
		        alert.setContentText("Le format HH:MM est attendu dans le champ : Heure de rendu.");
		        alert.showAndWait();
		        error = true;    
		  } else if(releaseDatePicker.getValue().isAfter(endDatePicker.getValue())) {
			  Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Erreur de date");
		        alert.setHeaderText("Erreur de date");
		        alert.setContentText("La date de rendu doit �tre apr�s la date de d�but.");
		        alert.showAndWait();
		        error = true;    
		  }else{
			  try {
					Integer.parseInt(NbElevesTextField.getText());
				} catch (NumberFormatException e){
					Alert alert = new Alert(AlertType.ERROR);
			        alert.setTitle("Erreur de type");
			        alert.setHeaderText("Erreur de type");
			        alert.setContentText("Un entier est attendu dans le champ : Nb d'�l�ve/groupe.");
			        alert.showAndWait();
			        error = true;
				}
			  if(!error){
				  discipline = matiereChoiceBox.getValue().toString();
				  title = titreTextField.getText();
				  description = descriptionText.getText();
				  releaseDate = releaseDatePicker.getValue();
				  deadline = endDatePicker.getValue();
				  heureFin = endHour.getText();
				  isPrivate = radioPrivate.isSelected() ? "true" : "false";
				  NbEleves = Integer.parseInt(NbElevesTextField.getText());
				  Main.mainPane.setCenter(Main.panel4);
			  }
			  
		  }
		  

		  log.debug("Create Devoir");
		  log.debug("isPrivate : " + isPrivate);
		  log.debug("Matiere : " +discipline);
		  log.debug("Titre : " + title);
		  log.debug("Text : " + description);
		  log.debug("ReleaseDate : " + releaseDate);
		  log.debug("EndDate : " + deadline + " � " + heureFin+"h\n");

		  
	  }
	  

	  @FXML
	  public void back2Menu(ActionEvent event) throws IOException {

			Main.mainPane.setCenter(Main.panel1);
		  
	  }
	  
	  

	@SuppressWarnings("unchecked")
	@FXML
	  public void initialize(){
		  matiereChoiceBox.setItems(list);
		  matiereChoiceBox.setValue("1"); 
		  radioPrivate.setSelected(true);
	  }
	
}
