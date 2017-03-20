package controllers.student;

import controllers.BaseTableController;
import controllers.observers.book.BookObservable;
import controllers.observers.student.StudentObservable;
import javafx.collections.FXCollections;
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
import validators.StudentValidator;

import java.net.URL;
import java.util.ResourceBundle;

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
    private TableView<Book> tvBooks;

    private StudentValidator studentValidator = new StudentValidator();
    private Student student;

    private static StudentInfoController instance;

    public static StudentInfoController getInstance() {
        return instance;
    }

    @Override
    protected TableView<Book> getTableView() {
        return tvBooks;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return null;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(bookService.getBookListByStudent(student));
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcDateOfGive.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfGive"));
        tcDateOfTake.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfTake"));
        tvBooks.setItems(observableList);
        tvBooks.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initListeners();
    }

    private void initListeners() {
        btnTakeBook.setOnAction(event -> {
            Book book = getSelectionItem();
            if (book != null) {
                bookService.takeBook(book);
                book.setAvailable(true);
                BookObservable.onBookTaken(book);
                for (int i = 0; i < observableList.size(); i++) {
                    Book currentBook = observableList.get(i);
                    if (currentBook.getId() == book.getId())
                        observableList.remove(i);
                }
            }
        });
        btnOk.setOnAction(event -> {
            if (!(tfName.getText().isEmpty()
                    || tfSurname.getText().isEmpty()
                    || tfMiddleName.getText().isEmpty())) {
                student.setName(tfName.getText());
                student.setSurname(tfSurname.getText());
                student.setMiddleName(tfMiddleName.getText());
                if (studentValidator.checkAllTextField(student)) {
                    studentService.updateStudent(student);
                    StudentObservable.onStudentEdit(studentService.getStudentById(student.getId()));
                    getStage().close();
                }
            }
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
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
