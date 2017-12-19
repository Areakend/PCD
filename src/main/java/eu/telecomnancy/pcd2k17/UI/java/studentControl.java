package eu.telecomnancy.pcd2k17.UI.java;

import javafx.scene.control.TextArea;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.telecomnancy.pcd2k17.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class studentControl {

	final static Logger log = LogManager.getLogger(studentControl.class);
	
	private String path = null;

	// public static Stage FileLoaderStage;

	@FXML
	TextField commitMessage = new TextField();

	@FXML
	TextArea fileList = new TextArea();

	@FXML
	Label test;

	@FXML
	public void validDevoir(ActionEvent event) throws IOException {
		
		
		/*
		 * Stage FileLoaderStage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow();
		 * FileLoaderStage.setScene(new Scene(Main.createDevoir, 1600, 800));
		 * FileLoaderStage.show();
		 */
		log.debug("Connexion button was clicked!");
		log.debug("Input CommitText : " + commitMessage.getText());

		Main.commitMessage = commitMessage.getText();
		log.debug("Push Devoir");
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
			Main.fileList[Main.nbofFiles] = path;
			Main.nbofFiles++;
			fileList.setText(fileList.getText() + path + "\n");
		}
		log.debug("path : " + path);
		
		log.debug("File List : " + Main.fileList[0]);
	}

}
