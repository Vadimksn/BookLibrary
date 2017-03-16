package controllers.student.tabs;

import controllers.BaseTableController;
import controllers.student.StudentInfoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Student;
import service.student.StudentService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 16.03.2017.
 */
public class StudentsController extends BaseTableController<Student> implements Initializable {
    @FXML
    private Button btnStudentInfo, btnStudentAddNew, btnDeleteStudent, btnAddToBlacklist;
    @FXML
    private TableColumn tcId, tcSurname, tcName, tcMiddleName, tcRegistrationDate;
    @FXML
    private TableView tvStudents;
    @FXML
    private TextField tfSearch;

    private static StudentsController instance;
    private ObservableList<Student> studentObservableList;
    private StudentService studentService = new StudentService();

    public static StudentsController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initTableData();
        setTextFieldFindBookListener();
        setButtonDeleteStudentListener();
        setButtonStudentAddToBlacklistListener();
        setButtonAddNewBookListener();
        setButtonStudentInfoListener();
    }

    private void setButtonStudentInfoListener() {
        btnStudentInfo.setOnAction(event -> {
            if (getSelectionItem() != null) {
                Stage stage = new Stage();
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                        .getResource("fxml/student/student_info_view.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Інформація читача");
                stage.setScene(new Scene(root));
                stage.show();
                StudentInfoController studentInfoController = loader.getController();
                studentInfoController.setStudent(getSelectionItem());
                studentInfoController.initStudentInfo(getSelectionItem());

            }
        });
    }

    private void setButtonAddNewBookListener() {
        btnStudentAddNew.setOnAction(event -> {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/student/student_add_new_view.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Додати нового читача");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private void setButtonDeleteStudentListener() {
        btnDeleteStudent.setOnAction(event -> {
            if (getSelectionItem() != null) {
                studentService.deleteStudent(getSelectionItem());
                studentObservableList.remove(getSelectedId());
            }
        });
    }

    private void setButtonStudentAddToBlacklistListener() {
        btnAddToBlacklist.setOnAction(event -> {
            if (getSelectionItem() != null) {
                studentService.addStudentToBlacklist(getSelectionItem());
                studentObservableList.remove(getSelectedId());
            }
        });
    }

    private void setTextFieldFindBookListener() {
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String stringForSearch = tfSearch.getText();

            if (stringForSearch.isEmpty()) {
                tvStudents.setItems(studentObservableList);
            } else {
                List<Student> bookListByString = new ArrayList<>();
                for (Student student : studentObservableList) {
                    if (student.toStringForSearch().contains(stringForSearch))
                        bookListByString.add(student);
                }
                ObservableList<Student> newList = FXCollections.observableArrayList(bookListByString);
                tvStudents.setItems(newList);
            }
        });
    }

    @Override
    public void initTableData() {
        studentObservableList = FXCollections.observableArrayList(studentService.getAllStudents());
        tcId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        tcMiddleName.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<Student, String>("registrationDate"));
        tvStudents.setItems(studentObservableList);
        tvStudents.setVisible(true);
    }

    @Override
    public Student getSelectionItem() {
        int id = getSelectedId();
        if (id != -1) {
            return studentObservableList.get(id);
        } else return null;
    }


    private int getSelectedId() {
        return tvStudents.getSelectionModel().getSelectedIndex();
    }


}
