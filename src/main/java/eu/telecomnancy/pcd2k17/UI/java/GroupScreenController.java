package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GroupScreenController {

	private String path;
	  
	@FXML
	Text pathText = new Text();
	
	@FXML
	  public void importCSV(ActionEvent event) {
		  
			final FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("CSV","*.csv"),
					new FileChooser.ExtensionFilter("ALL","*.*")
					);
			
			Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			File file = fileChooser.showOpenDialog(FileLoaderStage);
			path = file.getAbsolutePath();
			
			String doubleSlashPath = path.replace("\\", "\\\\");
			Path p1 = Paths.get(doubleSlashPath);	 
			pathText.setText(p1.getFileName().toString());
	  }
}
