package eu.telecomnancy.pcd2k17.UI.java;



import eu.telecomnancy.pcd2k17.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
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


    @FXML
    public void addStudent(ActionEvent event) throws IOException {
        if(firstNameTextField.getText().length()==0 || lastNameTextField.getText().length()==0 ||
                mailTextField.getText().length()==0 || groupTextField.getText().length()==0 ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formulaire(s) vide(s)");
            alert.setHeaderText("Formulaire(s) vide(s).");
            alert.setContentText("Veuillez remplir tous les formulaires.");

            alert.showAndWait();
        } else {
            prenom = firstNameTextField.getText();
            nom = lastNameTextField.getText();
            mail = mailTextField.getText();
            group = groupTextField.getText();
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            mailTextField.setText("");
            groupTextField.setText("");

            Main.stage2.hide();

        }

    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        mailTextField.setText("");
        groupTextField.setText("");
        Main.stage2.hide();


    }
}
