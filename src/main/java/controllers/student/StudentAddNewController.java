package controllers.student;

import controllers.observers.student.StudentObservable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;
import service.student.StudentService;
import validators.StudentValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentAddNewController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfSurname, tfName, tfMiddleName, tfPassportData;
    @FXML
    private AnchorPane apAddNewStudent;

    private StudentValidator studentValidator = new StudentValidator();
    private StudentService studentService = new StudentService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            Student student = new Student(tfName.getText(), tfSurname.getText(),
                    tfMiddleName.getText(), tfPassportData.getText());
            if (studentValidator.checkAllTextField(student)) {
                if (studentService.saveStudent(student)) {
                    StudentObservable.onStudentAdded(studentService.getLastAddedStudent());
                    getStage().close();
                }
            }
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    private Stage getStage() {
        return ((Stage) apAddNewStudent.getScene().getWindow());
    }
}
