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
import eu.telecomnancy.pcd2k17.api.ApiProjectReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.*;

@SuppressWarnings("unused")
public class pullScreen {

	final static Logger log = LogManager.getLogger(pullScreen.class);

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
	static String fichierChoisi = null;

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

		if (projetChoisi == null) {
			Alert connectionError = new Alert(AlertType.ERROR);
			connectionError.setTitle("Impossible de push");
			connectionError.setHeaderText(null);
			connectionError.setContentText("Il faut choisir un projet dans lequel push");
			connectionError.showAndWait();
		} else {
			log.debug("Push button was clicked!");

			// PULL

			try {

				// PULL

			} catch (Exception e) {
				Alert connectionError = new Alert(AlertType.ERROR);
				connectionError.setTitle("Erreur de pull");
				connectionError.setHeaderText(null);
				connectionError.setContentText("" + e);
				connectionError.showAndWait();
			}
		}
	}

	@FXML
	public void back2Menu(ActionEvent event) throws IOException {

		Main.mainPane.setCenter(Main.panel1);
	}

	@FXML
	public void choisirUnFichier(ActionEvent event) {
		
		ApiFile file = new ApiFile(new ApiProjectReturn("Rendu de projet " + projetChoisi,
				new ApiAssignment(new ApiDiscipline(matiereChoisie), projetChoisi)));
		
		LinkedList<String> files = file.getElements();
				
		ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", files);
		dialog.setTitle("Choix du fichier");
		dialog.setHeaderText(null);
		dialog.setContentText("Fichier du projet " + projetChoisi + ": ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			fichierChoisi = result.get();
			Main.fileListPull.add(fichierChoisi);
			Main.setNbofFiles(Main.getNbofFiles() + 1);
			fileList.setText(fileList.getText() + fichierChoisi + "\n");
		}
	}

}
