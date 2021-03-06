package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.api.ApiAssignment;
import eu.telecomnancy.pcd2k17.api.ApiConnect;
import eu.telecomnancy.pcd2k17.api.ApiDiscipline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objects.Project;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import org.gitlab4j.api.models.Visibility;

@SuppressWarnings("unused")
public class CreateAssignementController {
	private static Project CURRENTPROJECT = null;
	

	final static Logger log = LogManager.getLogger(ConnexionScreenController.class);
	private String discipline, title, description, heureFin, isPrivate, prefix;

	private LocalDate releaseDate, deadline;

	private int NbEleves;

	ObservableList<String> list = FXCollections.observableArrayList("1", "2");

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
	Text matiereText = new Text();

	@FXML
	RadioButton radioPrivate;

	@FXML
	RadioButton radioPublic;

	@FXML
	TextField NbElevesTextField = new TextField();
	
	static String matiereChoisie = null;
 /*

*/
	@FXML
	public void CreateDevoir(ActionEvent event) throws IOException {
		/*
		 * Stage FileLoaderStage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow();
		 * FileLoaderStage.setScene(new Scene(Main.createDevoir, 1600, 800));
		 * FileLoaderStage.show();
		 */
		boolean error = false;
		if (titreTextField.getText().length() == 0 || prefixTextField.getText().length() == 0
				|| descriptionText.getText().length() == 0 || releaseDatePicker.getValue() == null
				|| endDatePicker.getValue() == null || endHour.getText().length() == 0
				|| NbElevesTextField.getText().length() == 0) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Formulaire(s) vide(s)");
			alert.setHeaderText("Formulaire(s) vide(s).");
			alert.setContentText("Veuillez remplir tous les formulaires.");

			alert.showAndWait();
		} else if (!endHour.getText().matches("\\d{1,2}:\\d{1,2}")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur de format");
			alert.setHeaderText("Erreur de format");
			alert.setContentText("Le format HH:MM est attendu dans le champ : Heure de rendu.");
			alert.showAndWait();
			error = true;
		} else if (releaseDatePicker.getValue().isAfter(endDatePicker.getValue())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur de date");
			alert.setHeaderText("Erreur de date");
			alert.setContentText("La date de rendu doit etre apres la date de debut.");
			alert.showAndWait();
			error = true;
		} else {
			try {
				Integer.parseInt(NbElevesTextField.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur de type");
				alert.setHeaderText("Erreur de type");
				alert.setContentText("Un entier est attendu dans le champ : Nb d'eleve/groupe.");
				alert.showAndWait();
				error = true;
			}
			if (!error) {
				discipline = matiereText.getText();
				title = titreTextField.getText();
				description = descriptionText.getText();
				releaseDate = releaseDatePicker.getValue();
				deadline = endDatePicker.getValue();
				heureFin = endHour.getText();
				prefix = prefixTextField.getText();
				isPrivate = radioPrivate.isSelected() ? "true" : "false";
				NbEleves = Integer.parseInt(NbElevesTextField.getText());
				Main.mainPane.setCenter(Main.panel4);
			}

		}

		log.debug("Create Devoir");
		log.debug("isPrivate : " + isPrivate);
		log.debug("Matiere : " + discipline);
		log.debug("Titre : " + title);
		log.debug("Text : " + description);
		log.debug("Prefixe : " + prefix);
		log.debug("ReleaseDate : " + releaseDate);
		log.debug("EndDate : " + deadline + " � " + heureFin + "h\n");
		
		try {
			new ApiAssignment(new ApiDiscipline(discipline), title, description);
		}
		catch (Exception e){

		}
		CreateAssignementController.CURRENTPROJECT = new Project(title, prefix, deadline);
	}
	
	@FXML
	public void ajouterMatiere(ActionEvent event) throws IOException {
		
		List<String> matiere = ApiConnect.getInstance().getListDiscipline();

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Ajouter une matiere");
		dialog.setHeaderText(null);
		dialog.setContentText("Matiere :");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		try {
			if (result.isPresent()){
				new ApiDiscipline(result.get(), Visibility.PUBLIC);
			}
		}
		catch (Exception e) {
			Alert connectionError = new Alert(AlertType.ERROR);
			connectionError.setTitle("Error:");
			connectionError.setHeaderText(null);
			connectionError.setContentText("" + e);
			connectionError.showAndWait();
		}
	}
		
	@FXML
	public void choixMatiere(ActionEvent event) throws IOException {
		List<String> matiere = ApiConnect.getInstance().getListDiscipline();

		ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", matiere);
		dialog.setTitle("Choix de la matiere");
		dialog.setHeaderText(null);
		dialog.setContentText("Matieres :");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			matiereChoisie = result.get();
			matiereText.setText(matiereChoisie);

/*			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Veuillez choisir une liste d'�l�ves");

			alert.showAndWait();
			final FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"),
					new FileChooser.ExtensionFilter("ALL", "*.*"));

			Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			File file = fileChooser.showOpenDialog(FileLoaderStage);
			String path = file.getAbsolutePath();

			System.out.println("File : " + path);
*/
		}
	}

	@FXML
	public void back2Menu(ActionEvent event) throws IOException {

		Main.mainPane.setCenter(Main.panel1);

	}

	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
		matiereChoiceBox.setItems(list);
		matiereChoiceBox.setValue("1");
		radioPrivate.setSelected(true);
	}
	
	public static Project getCurrentProject(){
		return CreateAssignementController.CURRENTPROJECT;
	}

}
