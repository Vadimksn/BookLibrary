package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Book;

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
    private Button btnGiveBook, btnDeleteBook, btnChangeBook, btnAddNewBook;
    @FXML
    private TextField tfSearch;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
