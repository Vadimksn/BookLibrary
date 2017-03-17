package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.book.BookEditController;
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

import java.io.IOException;
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
        setButtonAddNewBookListener();
        setButtonDeleteBookListener();
        setButtonEditBookListener();
        setButtonGiveBookListener();
        setTextFieldFindBookListener();
    }

    private void setTextFieldFindBookListener() {
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search();
            }
        });
    }

    private void setButtonGiveBookListener() {
        btnGiveBook.setOnAction(event -> {
            if (getSelectionItem() != null && getSelectionItem().isAvailable()) {
                Stage stage = new Stage();
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/student/student_choose_view.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Оберіть студента");
                stage.setScene(new Scene(root));
                stage.show();
                StudentChooseController studentChooseController = loader.getController();
                studentChooseController.setBaseTableController(this);
            }
        });
    }

    private void setButtonDeleteBookListener() {
        btnDeleteBook.setOnAction(event -> {
            if (getSelectionItem() != null && getSelectionItem().isAvailable()) {
                bookService.deleteBook(getSelectionItem());
                observableList.remove(getSelectedId());
            }
        });
    }

    private void setButtonEditBookListener() {
        btnEditBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                Stage stage = new Stage();
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                        .getResource("fxml/book/book_edit_view.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Змінити книгу");
                stage.setScene(new Scene(root));
                stage.show();
                BookEditController bookEditController = loader.getController();
                bookEditController.initBookInfo(getSelectionItem());
            }
        });
    }


    private void setButtonAddNewBookListener() {
        btnAddNewBook.setOnAction(event -> {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/book/book_add_new_view.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Додати нову книгу");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
