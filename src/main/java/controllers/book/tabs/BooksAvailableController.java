package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.observers.book.BookObserver;
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
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksAvailableController extends BaseTableController<Book> implements Initializable, BookObserver {
    @FXML
    private TableView<Book> tvBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication;
    @FXML
    private Button btnGiveBook;
    @FXML
    private TextField tfSearch;

    @Override
    protected TableView<Book> getTableView() {
        return tvBooks;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return tfSearch;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(bookService.getAvailableBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("yearOfPublication"));
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
                observableList.set(i,book);
        }
    }

    @Override
    public void onBookGiven(Book book) {
        for (int i = 0; i < observableList.size(); i++) {
            Book currentBook = observableList.get(i);
            if (currentBook.getId() == book.getId())
                observableList.remove(i);
        }
    }

    @Override
    public void onBookTaken(Book book) {
        observableList.add(book);

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
    }
}
