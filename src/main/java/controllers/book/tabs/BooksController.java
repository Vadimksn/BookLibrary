package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.observers.book.BookObservable;
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
import controllers.observers.book.BookObserver;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController extends BaseTableController<Book> implements Initializable, BookObserver {
    @FXML
    private TableView<Book> tvBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication, tcAvailability;
    @FXML
    private Button btnGiveBook, btnDeleteBook, btnEditBook, btnAddNewBook;
    @FXML
    private TextField tfSearch;

    @Override
    public TableView<Book> getTableView() {
        return tvBooks;
    }

    @Override
    public TextField getTextFieldSearch() {
        return tfSearch;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(bookService.getAllBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("yearOfPublication"));
        tcAvailability.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("available"));
        tvBooks.setItems(observableList);
        tvBooks.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BookObservable.registerBookObserver(this);
        initTableData();
        initListeners();
    }

    @Override
    public void onBookAdded(Book book) {
        observableList.add(book);
    }

    @Override
    public void onBookDeleted(Book book) {
        for (int i = 0; i < observableList.size(); i++) {
            Book currentBook = observableList.get(i);
            if (currentBook.getId() == book.getId())
                observableList.remove(i);
        }
    }

    @Override
    public void onBookEdit(Book book) {
        for (int i = 0; i < observableList.size(); i++) {
            Book currentBook = observableList.get(i);
            if (currentBook.getId() == book.getId())
                observableList.set(i, book);
        }
    }

    @Override
    public void onBookGiven(Book book) {
        for (int i = 0; i < observableList.size(); i++) {
            Book currentBook = observableList.get(i);
            if (currentBook.getId() == book.getId())
                observableList.set(i, book);
        }
    }

    @Override
    public void onBookTaken(Book book) {
        for (int i = 0; i < observableList.size(); i++) {
            Book currentBook = observableList.get(i);
            if (currentBook.getId() == book.getId())
                observableList.set(i, book);
        }
    }

    private void initListeners() {
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search();
            }
        });
        btnGiveBook.setOnAction(event -> {
            if (getSelectionItem() != null && getSelectionItem().isAvailable()) {
                ViewUtil.showStudentChoose(getSelectionItem());
            }
        });

        btnDeleteBook.setOnAction(event -> {
            if (getSelectionItem() != null && getSelectionItem().isAvailable() && ViewUtil.showConfirmation()) {
                bookService.deleteBook(getSelectionItem());
                BookObservable.onBookDeleted(getSelectionItem());
            }
        });

        btnEditBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                ViewUtil.showBookEditView(this);
            }
        });
        btnAddNewBook.setOnAction(event -> {
            ViewUtil.showView(UiConstants.Path.BOOK_ADD_NEW_PATH, UiConstants.Tittle.BOOK_ADD_NEW_TITTLE, false);
        });
    }
}
