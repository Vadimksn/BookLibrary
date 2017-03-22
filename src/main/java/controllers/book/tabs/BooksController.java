package controllers.book.tabs;


import controllers.BaseTableController;
import controllers.observers.book.BookObservable;
import controllers.observers.book.BookObserver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Book;
import org.apache.log4j.Logger;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController extends BaseTableController<Book> implements Initializable, BookObserver {
    @FXML
    private TableView<Book> tvBooks;
    @FXML
    private TableColumn<Book, Integer> tcId;
    @FXML
    private TableColumn<Book, String> tcTitle, tcAuthor, tcEdition, tcYearOfPublication;
    @FXML
    private TableColumn<Book, Boolean> tcAvailability;
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
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
        tcAvailability.setCellValueFactory(new PropertyValueFactory<>("available"));
        tcAvailability.setCellFactory(new Callback<TableColumn<Book, Boolean>, TableCell<Book, Boolean>>() {
            @Override
            public TableCell<Book, Boolean> call(TableColumn<Book, Boolean> param) {
                return new TableCell<Book, Boolean>() {
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        // FIXME: 20.03.2017 say thanks to javafx developers o_O
                        if (item == null || empty)
                            setStyle("");
                        else if (item) {
                            setStyle("-fx-background-color: rgba(0, 128, 0, 0.4); -fx-background-radius: 5");
                        } else {
                            setStyle("-fx-background-color: rgba(255, 0, 0, 0.4); -fx-background-radius: 5");
                        }
                    }
                };
            }
        });
        tvBooks.setItems(observableList);
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
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> search());
        btnGiveBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                if (!getSelectionItem().isAvailable()) {
                    ViewUtil.showError(UiConstants.Dialogs.STUDENT_BOOK_ERROR);
                } else ViewUtil.showStudentChoose(getSelectionItem());
            }
        });

        btnDeleteBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                if (!getSelectionItem().isAvailable()) {
                    ViewUtil.showError(UiConstants.Dialogs.STUDENT_BOOK_ERROR);
                } else if (ViewUtil.showConfirmation()) {
                    bookService.deleteBook(getSelectionItem());
                    BookObservable.onBookDeleted(getSelectionItem());
                }
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
