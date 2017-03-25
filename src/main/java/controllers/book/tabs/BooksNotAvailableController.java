package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.observers.book.BookObservable;
import controllers.observers.book.BookObserver;
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
import model.Book;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksNotAvailableController extends BaseTableController<Book> implements Initializable, BookObserver {
    @FXML
    private TableView<Book> tvNotAvailableBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication, tcStudentId, tcDateOfGive, tcDateOfTake;
    @FXML
    private Button btnTakeBook;
    @FXML
    private TextField tfSearch;

    @Override
    protected TableView<Book> getTableView() {
        return tvNotAvailableBooks;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return tfSearch;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(bookService.getNotAvailableBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("yearOfPublication"));
        tcStudentId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("studentId"));
        tcDateOfGive.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfGive"));
        tcDateOfTake.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfTake"));
        tvNotAvailableBooks.setItems(observableList);
        tvNotAvailableBooks.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BookObservable.registerBookObserver(this);
        initTableData();
        initListeners();
    }

    private void initListeners() {
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> search());
        btnTakeBook.setOnAction(event -> {
            if (getSelectionItem() != null && ViewUtil.showConfirmation()) {
                bookService.takeBook(getSelectionItem());
                Book takenBook = getSelectionItem();
                takenBook.setAvailable(true);
                BookObservable.onBookTaken(takenBook);
            }
        });
    }

    @Override
    public void onBookAdded(Book book) {

    }

    @Override
    public void onBookDeleted(Book book) {

    }

    @Override
    public void onBookEdit(Book book) {
        for (int i = 0; i < observableList.size(); i++) {
            Book currentBook = observableList.get(i);
            if (currentBook.getId() == book.getId()) {
                book.setStudentId(currentBook.getStudentId());
                observableList.set(i, book);
            }
        }
    }

    @Override
    public void onBookGiven(Book book) {
        observableList.add(book);
    }

    @Override
    public void onBookTaken(Book book) {
        for (int i = 0; i < observableList.size(); i++) {
            Book currentBook = observableList.get(i);
            if (currentBook.getId() == book.getId())
                observableList.remove(i);
        }
    }
}
