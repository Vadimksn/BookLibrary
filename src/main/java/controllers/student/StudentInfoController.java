package controllers.student;

import controllers.BaseTableController;
import controllers.student.tabs.StudentsController;
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
import validators.StudentValidator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 16.03.2017.
 */
public class StudentInfoController extends BaseTableController<Book> implements Initializable {
    @FXML
    private Button btnOk, btnCancel, btnTakeBook;
    @FXML
    private TextField tfSurname, tfName, tfMiddleName, tfPassportData, tfRegistrationDate;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcDateOfGive, tcDateOfTake;
    @FXML
    private AnchorPane apStudentInfo;
    @FXML
    private TableView tvBooks;

    private StudentsController studentsController = StudentsController.getInstance();
    private StudentValidator studentValidator = new StudentValidator();
    private StudentService studentService = new StudentService();
    private ObservableList<Book> bookObservableList;
    private Student student;
    private BookService bookService = new BookService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBtnCancelListener();
        setBtnOkListener();
        setButtonTakeBookListener();
    }


    @Override
    public void initTableData() {
        bookObservableList = FXCollections.observableArrayList(studentService.getStudentBookList(student));
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcDateOfGive.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfGive"));
        tcDateOfTake.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfTake"));
        tvBooks.setItems(bookObservableList);
        tvBooks.setVisible(true);
    }

    @Override
    public Book getSelectionItem() {
        int id = getSelectedId();
        if (id != -1) {
            return bookObservableList.get(id);
        } else return null;
    }

    private int getSelectedId() {
        return tvBooks.getSelectionModel().getSelectedIndex();
    }

    private void setButtonTakeBookListener() {
        btnTakeBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                bookService.takeBook(getSelectionItem());
                initTableData();
            }
        });
    }


    private void setBtnOkListener() {
        btnOk.setOnAction(event -> {
            if (!(tfName.getText().isEmpty()
                    || tfSurname.getText().isEmpty()
                    || tfMiddleName.getText().isEmpty()
                    || tfPassportData.getText().isEmpty())) {
                student.setName(tfName.getText());
                student.setSurname(tfSurname.getText());
                student.setMiddleName(tfMiddleName.getText());
                student.setPassportData(tfPassportData.getText());
                if (studentValidator.checkAllTextField(student)) {
                    studentService.updateStudent(student);
                    studentsController.initTableData();
                    getStage().close();
                }
            }
        });
    }

    private void setBtnCancelListener() {
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void initStudentInfo(Student student) {
        this.student = student;
        tfName.setText(student.getName());
        tfSurname.setText(student.getSurname());
        tfMiddleName.setText(student.getMiddleName());
        tfRegistrationDate.setText(student.getRegistrationDate());
        tfPassportData.setText(student.getPassportData());

        initTableData();
    }

    private Stage getStage() {
        return ((Stage) apStudentInfo.getScene().getWindow());
    }
}
