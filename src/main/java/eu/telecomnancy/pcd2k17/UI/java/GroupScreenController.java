package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.api.ApiConnect;
import eu.telecomnancy.pcd2k17.model.Student;
import eu.telecomnancy.pcd2k17.api.ApiAssignment;
import eu.telecomnancy.pcd2k17.api.ApiDiscipline;
import eu.telecomnancy.pcd2k17.api.ApiProjectReturn;
import eu.telecomnancy.pcd2k17.model.Students;
import java.util.LinkedList;
import java.util.List;
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
import objects.Project;
import org.gitlab4j.api.models.User;

@SuppressWarnings("unused")
public class GroupScreenController {

    final static Logger log = LogManager.getLogger(GroupScreenController.class);

	private String path;
    private ObservableList<Student> studentData;
	private List<Integer> listUserId = new LinkedList<>();

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
	public void deleteStudent(ActionEvent event) {

	}

	@FXML
	public void modifyStudent(ActionEvent event) {

	}

	@FXML
	public void generateGroups(ActionEvent event) {

	}




	public void validate(ActionEvent event) {
		try{
			Project project = CreateAssignementController.getCurrentProject();
			ApiDiscipline disc = new ApiDiscipline(project.getTitre());

			for (int id : listUserId) {

				ApiProjectReturn p = new ApiProjectReturn("["+project.getPrefix()+"]Rendu_de_projet_"+project.getTitre()+"_"+ ApiConnect.USER.getUser(id).getUsername(),
						new ApiAssignment(disc, project.getTitre()));
				p.addMembers(p.getIdProject(), id);
			}
		}
		catch (Exception e){
			Alert connectionError = new Alert(AlertType.ERROR);
			connectionError.setTitle("Error:");
			connectionError.setHeaderText(null);
			connectionError.setContentText("Try again : " + e);
			connectionError.showAndWait();
		}
	}

	@FXML
	public void importCSV(ActionEvent event) {

		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"),
				new FileChooser.ExtensionFilter("ALL", "*.*"));

		Stage FileLoaderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		File file = fileChooser.showOpenDialog(FileLoaderStage);
		path = file.getAbsolutePath();

		String doubleSlashPath = path.replace("\\", "\\\\");
		Path p1 = Paths.get(doubleSlashPath);
		pathText.setText(p1.getFileName().toString());
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
    
	@FXML
	public void back2Menu(ActionEvent event) throws IOException {

		Main.mainPane.setCenter(Main.panel1);

	}

	public void AddStudent(ActionEvent event) throws IOException {
		Scene scene = new Scene(Main.panel5, 400, 400);
		Main.stage2.setTitle("Ajouter un élève");
		Main.stage2.setScene(scene);
		Main.stage2.show();

	}

}
