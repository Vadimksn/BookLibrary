package controllers.student.tabs;

import controllers.BaseTableController;
import controllers.student.StudentInfoController;
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
import utils.ui.UiPathConstants;
import utils.ui.UiTitleConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController extends BaseTableController<Student> implements Initializable {
    @FXML
    private Button btnStudentInfo, btnStudentAddNew, btnDeleteStudent, btnAddToBlacklist;
    @FXML
    private TableColumn tcId, tcSurname, tcName, tcMiddleName, tcRegistrationDate;
    @FXML
    private TableView<Student> tvStudents;
    @FXML
    private TextField tfSearch;

    private static StudentsController instance;

    public static StudentsController getInstance() {
        return instance;
    }

    @Override
    protected TableView<Student> getTableView() {
        return tvStudents;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return tfSearch;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(studentService.getAllStudents());
        tcId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        tcMiddleName.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<Student, String>("registrationDate"));
        tvStudents.setItems(observableList);
        tvStudents.setVisible(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initTableData();
        initListeners();
    }

    private void initListeners() {
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search();
            }
        });
        btnStudentInfo.setOnAction(event -> {
            if (getSelectionItem() != null) {
                ViewUtil.showView(UiPathConstants.STUDENT_INFO_PATH, UiTitleConstants.STUDENT_INFO_TITTLE);
                StudentInfoController.getInstance().initStudentInfo(getSelectionItem());
            }
        });
        btnStudentAddNew.setOnAction(event -> {
            ViewUtil.showView(UiPathConstants.STUDENT_ADD_NEW_PATH,UiTitleConstants.STUDENT_ADD_NEW_TITTLE);
        });
        btnDeleteStudent.setOnAction(event -> {
            if (getSelectionItem() != null && bookService.getBookListByStudent(getSelectionItem()).size() == 0) {
                studentService.deleteStudent(getSelectionItem());
                observableList.remove(getSelectedId());
            }
        });
        btnAddToBlacklist.setOnAction(event -> {
            if (getSelectionItem() != null && bookService.getBookListByStudent(getSelectionItem()).size() == 0) {
                studentService.addStudentToBlacklist(getSelectionItem());
                observableList.remove(getSelectedId());
            }
        });
    }
}
