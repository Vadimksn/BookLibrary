package controllers.student.tabs;

import controllers.BaseTableController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import service.student.StudentService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 16.03.2017.
 */
public class StudentsBlacklistController extends BaseTableController<Student> implements Initializable {
    @FXML
    private Button btnDeleteStudentFromBlacklist;
    @FXML
    private TableColumn tcId, tcSurname, tcName, tcMiddleName, tcRegistrationDate, tcPassportData, tcBlacklistDate;
    @FXML
    private TableView tvBlackList;
    @FXML
    private TextField tfSearch;

    private static StudentsBlacklistController instance;
    private ObservableList<Student> studentObservableList;
    private StudentService studentService = new StudentService();

    public static StudentsBlacklistController getInstance() {
        return instance;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        setTextFieldFindBookListener();
        setBtnDeleteStudentFromBlacklistListener();
    }

    private void setBtnDeleteStudentFromBlacklistListener() {
        btnDeleteStudentFromBlacklist.setOnAction(event -> {
            if (getSelectionItem() != null) {
                studentService.deleteStudentFromBlackList(getSelectionItem());
                studentObservableList.remove(getSelectedId());
            }
        });
    }

    @Override
    public void initTableData() {
        studentObservableList = FXCollections.observableArrayList(studentService.getStudentsBlacklist());
        tcId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        tcMiddleName.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<Student, String>("registrationDate"));
        tcPassportData.setCellValueFactory(new PropertyValueFactory<Student, String>("passportData"));
        tcBlacklistDate.setCellValueFactory(new PropertyValueFactory<Student, String>("blacklistDate"));
        tvBlackList.setItems(studentObservableList);
        tvBlackList.setVisible(true);
    }

    @Override
    public Student getSelectionItem() {
        int id = getSelectedId();
        if (id != -1) {
            return studentObservableList.get(id);
        } else return null;
    }


    private int getSelectedId() {
        return tvBlackList.getSelectionModel().getSelectedIndex();
    }

    private void setTextFieldFindBookListener() {
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String stringForSearch = tfSearch.getText();

            if (stringForSearch.isEmpty()) {
                tvBlackList.setItems(studentObservableList);
            } else {
                List<Student> bookListByString = new ArrayList<>();
                for (Student student : studentObservableList) {
                    if (student.toStringForSearch().contains(stringForSearch))
                        bookListByString.add(student);
                }
                ObservableList<Student> newList = FXCollections.observableArrayList(bookListByString);
                tvBlackList.setItems(newList);
            }
        });
    }
}
