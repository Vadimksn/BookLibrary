package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.student.StudentChooseController;
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
import model.Book;
import model.Student;
import service.book.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 15.03.2017.
 */
public class BooksNotAvailableController extends BaseTableController<Book> implements Initializable {
    @FXML
    private TableView<Book> tvNotAvailableBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication,tcStudentId,tcDateOfGive,tcDateOfTake;
    @FXML
    private Button btnTakeBook;
    @FXML
    private TextField tfSearch;

    private static BooksNotAvailableController instance;
    private ObservableList<Book> bookObservableList;
    private BookService bookService = new BookService();

    public static BooksNotAvailableController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initTableData();
        setButtonTakeBookListener();
        setTextFieldFindBookListener();
    }

    private void setButtonTakeBookListener() {
        btnTakeBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                bookService.takeBook(getSelectionItem());
                initTableData();
            }
        });
    }

    private void setTextFieldFindBookListener() {
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String stringForSearch = tfSearch.getText();

            if (stringForSearch.isEmpty()) {
                tvNotAvailableBooks.setItems(bookObservableList);
            } else {
                List<Book> bookListByString = new ArrayList<>();
                for (Book book : bookObservableList) {
                    if (book.toStringForSearch().contains(stringForSearch))
                        bookListByString.add(book);
                }
                ObservableList<Book> newList = FXCollections.observableArrayList(bookListByString);
                tvNotAvailableBooks.setItems(newList);
            }
        });
    }

    @Override
    public void initTableData() {
        bookObservableList = FXCollections.observableArrayList(bookService.getNotAvailableBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("yearOfPublication"));
        tcStudentId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("studentId"));
        tcDateOfGive.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfGive"));
        tcDateOfTake.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfTake"));
        tvNotAvailableBooks.setItems(bookObservableList);
        tvNotAvailableBooks.setVisible(true);
    }

    @Override
    public Book getSelectionItem() {
        int id = getSelectedId();
        if (id != -1) {
            return bookObservableList.get(id);
        } else return null;
    }

    private int getSelectedId() {
        return tvNotAvailableBooks.getSelectionModel().getSelectedIndex();
    }
}
