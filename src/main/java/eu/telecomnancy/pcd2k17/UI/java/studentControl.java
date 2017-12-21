package eu.telecomnancy.pcd2k17.UI.java;

import javafx.scene.control.TextArea;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.api.ApiAssignment;
import eu.telecomnancy.pcd2k17.api.ApiConnect;
import eu.telecomnancy.pcd2k17.api.ApiDiscipline;
import eu.telecomnancy.pcd2k17.api.ApiFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.*;

@SuppressWarnings("unused")
public class studentControl {

	final static Logger log = LogManager.getLogger(studentControl.class);
	
	private String path = null;

	// public static Stage FileLoaderStage;

	@FXML
	TextField commitMessage = new TextField();

	@FXML
	TextArea fileList = new TextArea();
	
	@FXML
	Text matiereText = new Text();
	
	@FXML	
	Text Title = new Text();
	
	@FXML
	Text HeureRendu = new Text();
	
	@FXML
	Text DateRendu = new Text();

	@FXML
	Label test;
	
	static String matiereChoisie = null;

	static String projetChoisi = null;
	
	public void choisirMatiere(ActionEvent event) throws IOException {
		
		List<String> matiere = ApiConnect.getInstance().getListDiscipline();

		ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", matiere);
		dialog.setTitle("Choix de la matière");
		dialog.setHeaderText(null);
		dialog.setContentText("Matières :");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			matiereChoisie = result.get();
			ApiDiscipline disp = new ApiDiscipline(matiereChoisie);
			List<String> projets = disp.getListAssignments();
			
			ChoiceDialog<String> dialog1 = new ChoiceDialog<>(" ", projets);
			dialog1.setTitle("Choix du projet");
			dialog1.setHeaderText(null);
			dialog1.setContentText("Projet(s) de " + matiereChoisie + ": ");
			Optional<String> result2 = dialog1.showAndWait();
			if (result2.isPresent()) {
				projetChoisi = result2.get();
				matiereText.setText(matiereChoisie);
				Title.setText(projetChoisi);
			}	
		}
	}
	@FXML
	public void validDevoir(ActionEvent event) throws IOException {

		log.debug("Connexion button was clicked!");
		log.debug("Input CommitText : " + commitMessage.getText());

		Main.setCommitMessage(commitMessage.getText());
		log.debug("Push Devoir");
		
//		ApiFile file = new ApiFile(new ApiAssignment(new ApiDiscipline(matiereChoisie), projetChoisi).getProject(projetChoisi));
	}

	  @FXML
	  public void back2Menu(ActionEvent event) throws IOException {

			Main.mainPane.setCenter(Main.panel1);  
	  }
	
	@FXML
	public void importFile(ActionEvent event) {

		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("ALL", "*.*"),
				new FileChooser.ExtensionFilter("CSV", "*.csv")
				);

		Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		File file = fileChooser.showOpenDialog(FileLoaderStage);
		// Main.path = file.getAbsolutePath();

		if (path != file.getAbsolutePath()) {
			path = file.getAbsolutePath();
			Main.fileList[Main.getNbofFiles()] = path;
			Main.setNbofFiles(Main.getNbofFiles()+1);
			fileList.setText(fileList.getText() + path + "\n");
		}
		log.debug("path : " + path);
		
		log.debug("File List : " + Main.fileList[0]);
	}

}
