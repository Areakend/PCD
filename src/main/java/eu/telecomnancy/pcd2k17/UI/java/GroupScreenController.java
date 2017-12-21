package eu.telecomnancy.pcd2k17.UI.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.api.ApiAssignment;
import eu.telecomnancy.pcd2k17.api.ApiDiscipline;
import eu.telecomnancy.pcd2k17.api.ApiProjectReturn;
import eu.telecomnancy.pcd2k17.model.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objects.Project;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;

@SuppressWarnings("unused")
public class GroupScreenController {

	private String path;
	private List<Integer> listUserId = new LinkedList<>();

	@FXML
	Text pathText = new Text();

	TreeTableView<String> groupsTreeTableView;

	@FXML
	private TreeTableColumn<Students, String> firstNameTableColumn;

	@FXML
	private TreeTableColumn<Students, String> lastNameTableColumn;

	@FXML
	private TreeTableColumn<Students, String> mailTableColumn;

	@FXML
	private TreeTableColumn<Students, String> groupsTableColumn;

	@FXML
	public void deleteStudent(ActionEvent event) {

	}

	@FXML
	public void modifyStudent(ActionEvent event) {

	}

	@FXML
	public void generateGroups(ActionEvent event) {

	}

	@FXML
	public void validate(ActionEvent event) {
		Project project = CreateAssignementController.getCurrentProject();
		ApiDiscipline disc = new ApiDiscipline(project.getTitre());

		for (Integer id : listUserId) {
			ApiProjectReturn p = new ApiProjectReturn(project.getPrefix(), project.getTitre(),
					new ApiAssignment(disc, project.getTitre()));
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
	public void AddStudent(ActionEvent event) throws IOException {
		Scene scene = new Scene(Main.panel5, 400, 400);
		Main.stage2.setTitle("Ajouter un élève");
		Main.stage2.setScene(scene);
		Main.stage2.show();

	}

}
