package controllers.book;

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
import service.book.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 13.03.2017.
 */
public class BooksController implements Initializable {
    @FXML
    private TableView<Book> tvBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication, tcAvailability;
    @FXML
    private Button btnGiveBook, btnDeleteBook, btnEditBook, btnAddNewBook;
    @FXML
    private TextField tfSearch;

    private ObservableList<Book> bookList;
    private BookService bookService = new BookService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        setButtonAddNewBookListener();
        setButtonDeleteBookListener();
        setButtonEditBookListener();
    }


    private void setButtonDeleteBookListener() {
        btnDeleteBook.setOnAction(event -> {
            if (getSelectionBook() != null) {
                bookService.deleteBook(getSelectionBook());
                bookList.remove(getSelectedId());
            }
        });
    }

    private void setButtonEditBookListener() {
        btnEditBook.setOnAction(event -> {
            if (getSelectionBook() != null) {
                Stage stage = new Stage();
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/book/edit_book_view.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Змінити книгу");
                stage.setScene(new Scene(root));
                stage.show();
                EditBookController editBookController = loader.getController();
                editBookController.setStage(stage);
                editBookController.initBookInfo(getSelectionBook());
            }
        });
    }

    private void setButtonAddNewBookListener() {
        btnAddNewBook.setOnAction(event -> {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/book/add_new_book_view.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Додати нову книгу");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private void initData() {
        bookList = FXCollections.observableArrayList(bookService.getAllBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("yearOfPublication"));
        tcAvailability.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("available"));
        tvBooks.setItems(bookList);
        tvBooks.setVisible(true);
    }

    private Book getSelectionBook() {
        int id = getSelectedId();
        if (id != -1) {
            return bookList.get(id);
        } else return null;
    }

    private int getSelectedId() {
        return tvBooks.getSelectionModel().getSelectedIndex();
    }
}
