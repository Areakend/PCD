package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.model.Student;
import eu.telecomnancy.pcd2k17.model.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.scene.control.cell.PropertyValueFactory;

public class GroupScreenController {

    final static Logger log = LogManager.getLogger(GroupScreenController.class);

	private String path;
    private Scene addScene;
    private Stage addStage;
    private ObservableList<Student> studentData;

	@FXML
	Text pathText = new Text();

	@FXML
	private TableView<Student> groupsView;

    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> mailColumn;
    @FXML
    private TableColumn<Student, String> groupColumn;
	
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

	@FXML
	public void AddStudent() throws IOException {




	}

	@FXML
    public void refresh() throws IOException{
        Students students = new Students();
        studentData = students.getAllStudents();
        groupsView.setItems(studentData);
    }

    @FXML
    public void initialize(){
        Students students = new Students();
        studentData = students.getAllStudents();
        studentData.add(new Student("Tanguy","Roudaut","tanguy.roudaut@telecomnancy.eu","Banana"));
        studentData.forEach(student ->student.afficherStudent());

        groupsView.setItems(studentData);
    }






}
