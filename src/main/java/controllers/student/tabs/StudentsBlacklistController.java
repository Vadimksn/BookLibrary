package controllers.student.tabs;

import controllers.BaseTableController;
import controllers.callbacks.student.StudentCallback;
import controllers.callbacks.student.StudentObservable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsBlacklistController extends BaseTableController<Student> implements Initializable, StudentCallback {
    @FXML
    private Button btnDeleteStudentFromBlacklist;
    @FXML
    private TableColumn tcId, tcSurname, tcName, tcMiddleName, tcRegistrationDate, tcPassportData, tcBlacklistDate;
    @FXML
    private TableView<Student> tvBlackList;
    @FXML
    private TextField tfSearch;

    @Override
    protected TableView<Student> getTableView() {
        return tvBlackList;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return tfSearch;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(studentService.getStudentsBlacklist());
        tcId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        tcMiddleName.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<Student, String>("registrationDate"));
        tcPassportData.setCellValueFactory(new PropertyValueFactory<Student, String>("passportData"));
        tcBlacklistDate.setCellValueFactory(new PropertyValueFactory<Student, String>("blacklistDate"));
        tvBlackList.setItems(observableList);
        tvBlackList.setVisible(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StudentObservable.registerStudentCallback(this);
        initListeners();
        initTableData();
    }

    private void initListeners() {
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search();
            }
        });
        btnDeleteStudentFromBlacklist.setOnAction(event -> {
            if (getSelectionItem() != null) {
                studentService.deleteStudentFromBlackList(getSelectionItem());
                StudentObservable.onStudentDeletedFromBlackList(getSelectionItem());
            }
        });

    }

    @Override
    public void onStudentAdded(Student student) {

    }

    @Override
    public void onStudentDeleted(Student student) {
    }

    @Override
    public void onStudentEdit(Student student) {
    }

    @Override
    public void onStudentAddedToBlacklist(Student student) {
        observableList.add(student);
    }

    @Override
    public void onStudentDeletedFromBlackList(Student student) {
        for (int i = 0; i < observableList.size(); i++) {
            Student currentStudent = observableList.get(i);
            if (currentStudent.getId() == student.getId())
                observableList.remove(i);
        }
    }
}
