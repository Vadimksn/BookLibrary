package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.student.StudentChooseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import utils.ui.ViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BooksAvailableController extends BaseTableController<Book> implements Initializable {
    @FXML
    private TableView<Book> tvBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication;
    @FXML
    private Button btnGiveBook;
    @FXML
    private TextField tfSearch;

    private static BooksAvailableController instance;

    public static BooksAvailableController getInstance() {
        return instance;
    }

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
    }
}
