package eu.telecomnancy.pcd2k17.UI.java;



import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.model.Student;
import eu.telecomnancy.pcd2k17.model.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderRepeat;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


import java.io.IOException;


public class addStudentController {

    @FXML
    TextField firstNameTextField;
    @FXML
    TextField lastNameTextField;
    @FXML
    TextField mailTextField;
    @FXML
    TextField groupTextField;

    private String prenom,nom,mail,group;
    private int i=10;
    private Student student;


    @FXML
    public Student addStudent(ActionEvent event) throws IOException {
        Student student;
        if(firstNameTextField.getText().length()==0 || lastNameTextField.getText().length()==0 ||
                mailTextField.getText().length()==0 || groupTextField.getText().length()==0 ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formulaire(s) vide(s)");
            alert.setHeaderText("Formulaire(s) vide(s).");
            alert.setContentText("Veuillez remplir tous les formulaires.");

            alert.showAndWait();
            student = new Student();
        } else {
            prenom = firstNameTextField.getText();
            nom = lastNameTextField.getText();
            mail = mailTextField.getText();
            group = groupTextField.getText();
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            mailTextField.setText("");
            groupTextField.setText("");
            student = new Student(i++,prenom+"."+nom,mail,group);
            Main.stage2.hide();
            Students.insertStudent(i,prenom+"."+nom,mail,"","",group);

        }
        return student;
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        mailTextField.setText("");
        groupTextField.setText("");
        Main.stage2.close();
    }

    public void setStudent(Student student) {
        this.student = student;

        firstNameTextField.setText(student.getUsername());
        lastNameTextField.setText(student.getUsername());
        mailTextField.setText(student.getMail());
        groupTextField.setText(student.getGroupName());

    }
}
