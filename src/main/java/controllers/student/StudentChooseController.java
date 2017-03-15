package controllers.student;

import controllers.BaseTableController;
import controllers.book.tabs.BooksController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import model.Student;
import service.book.BookService;
import service.student.StudentService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 15.03.2017.
 */
public class StudentChooseController implements Initializable {
    @FXML
    private AnchorPane apStudentChose;
    @FXML
    private TableView tvChooseStudent;
    @FXML
    private TableColumn tcId, tcSurname, tcName, tcMiddleName, tcPassportData;
    @FXML
    private Button btnОк, btnCancel;
    @FXML
    private TextField tfSearch;

    private ObservableList<Student> studentObservableList;
    private StudentService studentService = new StudentService();
    private BookService bookService = new BookService();
    private BaseTableController<Book> baseTableController;

    public void setBaseTableController(BaseTableController<Book> baseTableController) {
        this.baseTableController = baseTableController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        setBtnCancelListener();
        setBtnOkListener();
        setTextFieldFindContactListener();
    }

    private void setTextFieldFindContactListener() {
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String stringForSearch = tfSearch.getText();

            if (stringForSearch.isEmpty()) {
                tvChooseStudent.setItems(studentObservableList);
            } else {
                List<Student> studentListByString = new ArrayList<>();
                for (Student student : studentObservableList) {
                    if (student.toStringForSearch().contains(stringForSearch))
                        studentListByString.add(student);
                }
                ObservableList<Student> newList = FXCollections.observableArrayList(studentListByString);
                tvChooseStudent.setItems(newList);
            }
        });
    }

    public void initData() {
        studentObservableList = FXCollections.observableArrayList(studentService.getAllStudents());
        tcId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tcMiddleName.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        tcPassportData.setCellValueFactory(new PropertyValueFactory<Student, String>("passportData"));
        tvChooseStudent.setItems(studentObservableList);
        tvChooseStudent.setVisible(true);
    }

    private void setBtnOkListener() {
        btnОк.setOnAction(event -> {
            if (getSelectionStudent() != null) {
                bookService.giveBook(baseTableController.getSelectionItem(), getSelectionStudent());
                baseTableController.initTableData();
                getStage().close();
            }
        });
    }

    private void setBtnCancelListener() {
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    private Student getSelectionStudent() {
        int id = getSelectedId();
        if (id != -1) {
            return studentObservableList.get(id);
        } else return null;
    }


    private int getSelectedId() {
        return tvChooseStudent.getSelectionModel().getSelectedIndex();
    }

    private Stage getStage() {
        return ((Stage) apStudentChose.getScene().getWindow());
    }

}
