package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.model.Groups;
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
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn.CellDataFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.Project;

@SuppressWarnings("unused")
public class GroupScreenController {

    final static Logger log = LogManager.getLogger(GroupScreenController.class);

	private String path,mailStudent;
    private ObservableList<Student> studentData;
	private List<Integer> listUserId = new LinkedList<>();
    private Scene addScene,modifScene;
    private int addOpen = 0,modifOpen = 0,selectedIndex;

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
            int selectedIndex = groupsView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Student student = groupsView.getItems().get(selectedIndex);
                LinkedList<String> list= Groups.getGroupsbyIdStudent(student.getIdStudent());
                log.debug(""+list.size());
                Students.deleteStudent(student.getIdStudent());
                groupsView.getItems().remove(selectedIndex);
            } else {
                // Nothing selected
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Nothing selected");
                alert.setHeaderText("Nothing selected");
                alert.setContentText("Aucun élève n'est sélectionné.");
                alert.showAndWait();
            }
	}

	@FXML
	public void modifyStudent(ActionEvent event) {
        selectedIndex = groupsView.getSelectionModel().getSelectedIndex();
        System.out.print("Index : " + selectedIndex + "\n");
        if (selectedIndex >= 0) {
            if (modifOpen == 0){
                modifScene = new Scene(Main.panel7,400,400);
                modifOpen = 1;
            }
            mailStudent = groupsView.getItems().get(selectedIndex).getMail();

            System.out.print("Mail : " + mailStudent + "\n");
            Main.stage2.setTitle("Modifier un élève");
            Main.stage2.setScene(modifScene);
            Main.stage2.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Nothing selected");
            alert.setHeaderText("Nothing selected");
            alert.setContentText("Aucun élève n'est sélectionné.");
            alert.showAndWait();

        }



	}

    public void AddStudent(ActionEvent event) throws IOException {
        if(addOpen ==0){
            addScene = new Scene(Main.panel5, 400, 400);
            addOpen =1;
        }
        Main.stage2.setTitle("Ajouter un élève");
        Main.stage2.setScene(addScene);
        Main.stage2.show();

    }

	@FXML
	public void generateGroups(ActionEvent event) {

	}




	public void validate(ActionEvent event) {
		Project project = CreateAssignementController.getCurrentProject();
		ApiDiscipline disc = new ApiDiscipline(project.getTitre());

		for (Integer id : listUserId) {
			ApiProjectReturn p = new ApiProjectReturn(project.getTitre(),
					new ApiAssignment(disc, project.getTitre())).setPrefix(project.getPrefix());
			p.addMembers(p.getIdProject(), id);
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
        studentData = Students.getAllStudents();
        groupsView.setItems(studentData);
    }

    @FXML
    public void initialize(){
        studentData = Students.getAllStudents();
        //studentData.add(new Student("Tanguy","Roudaut","tanguy.roudaut@telecomnancy.eu","Banana"));
        studentData.forEach(student ->student.afficherStudent());
        groupsView.setItems(studentData);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
}
