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
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;

public class GroupScreenController {

	private String path;
	  
	@FXML
	Text pathText = new Text();
	
	TreeTableView<String> groupsTreeTableView;
	 
	@FXML
	private TreeTableColumn<String, String> firstNameTableColumn;
	
	@FXML
	private TreeTableColumn<String, String> lastNameTableColumn;
	
	@FXML
	private TreeTableColumn<String, String> mailTableColumn;
	
	@FXML
	private TreeTableColumn<String, String> groupsTableColumn;
	
	@FXML
	public void AddStudent(ActionEvent event){
		
	}
	
	@FXML
	public void deleteStudent(ActionEvent event){
		
	}
	
	@FXML
	public void modifyStudent(ActionEvent event){
		
	}
	
	@FXML
	public void generateGroups(ActionEvent event){
		
	}
	
	@FXML
	public void validate(ActionEvent event){
		
	}
	
	
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
