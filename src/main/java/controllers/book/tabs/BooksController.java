package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.book.BookEditController;
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
import utils.ui.UiPathConstants;
import utils.ui.UiTitleConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController extends BaseTableController<Book> implements Initializable {
    @FXML
    private TableView<Book> tvBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication, tcAvailability;
    @FXML
    private Button btnGiveBook, btnDeleteBook, btnEditBook, btnAddNewBook;
    @FXML
    private TextField tfSearch;

    private static BooksController instance;

    public static BooksController getInstance() {
        return instance;
    }

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
        btnGiveBook.setOnAction(event -> {
            if (getSelectionItem() != null && getSelectionItem().isAvailable()) {
                ViewUtil.showStudentChoose(this);
            }
        });

        btnDeleteBook.setOnAction(event -> {
            if (getSelectionItem() != null && getSelectionItem().isAvailable()) {
                bookService.deleteBook(getSelectionItem());
                observableList.remove(getSelectedId());
            }
        });

        btnEditBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                ViewUtil.showBookEditView(this);
            }
        });
        btnAddNewBook.setOnAction(event -> {
            ViewUtil.showView(UiPathConstants.BOOK_ADD_NEW_PATH, UiTitleConstants.BOOK_ADD_NEW_TITTLE);
        });
    }
}
