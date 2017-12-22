package eu.telecomnancy.pcd2k17.UI.java;



import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.telecomnancy.pcd2k17.Main;
import eu.telecomnancy.pcd2k17.model.Student;
import eu.telecomnancy.pcd2k17.model.Students;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.IOException;


public class ModificationStudentController {

    @FXML
    TextField firstNameTextField;
    @FXML
    TextField lastNameTextField;
    @FXML
    TextField mailTextField;
    @FXML
    TextField groupTextField;
    @FXML
    Button button1;

    private String prenom,nom,mail,group;
    private int i=10;
    private Student student;
    public static String mailStudentSelected;


    @FXML
    public void editFirstName(ActionEvent event){
        System.out.print(mailStudentSelected);
        ObservableList<Student> list = Students.getStudentsby("mail", mailStudentSelected);
        if(list.size()!=1){
            System.out.print(list.size());
        }
        else {
            setStudent(list.get(0));
        }

    }

    @FXML
    public void editLastName(ActionEvent event){
        ObservableList<Student> list = Students.getStudentsby("mail", mailStudentSelected);
        if(list.size()!=1){
            System.out.print(list.size());
        }
        else {
            setStudent(list.get(0));
        }

    }

    @FXML
    public void editMail(ActionEvent event){
        ObservableList<Student> list = Students.getStudentsby("mail", mailStudentSelected);
        if(list.size()!=1){
            System.out.print(list.size());
        }
        else {
            setStudent(list.get(0));
        }

    }

    @FXML
    public void editGroupe(ActionEvent event){
        ObservableList<Student> list = Students.getStudentsby("mail", mailStudentSelected);
        if(list.size()!=1){
            System.out.print(list.size());
        }
        else {
            setStudent(list.get(0));
        }

    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        mailTextField.setText("");
        groupTextField.setText("");
        Main.stage2.close();
    }

    public void modifyStudent(ActionEvent event) {
        this.student = student;

        firstNameTextField.setText(student.getUsername());
        lastNameTextField.setText(student.getUsername());
        mailTextField.setText(student.getMail());
        groupTextField.setText(student.getGroupName());

    }

    public void setStudent(Student student) {
        System.out.print("debug");
        firstNameTextField.setText(student.getUsername());
        lastNameTextField.setText(student.getUsername());
        mailTextField.setText(student.getMail());
        groupTextField.setText(student.getGroupName());

    }

}
