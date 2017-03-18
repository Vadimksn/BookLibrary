package controllers.book;

import controllers.book.tabs.BooksController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import service.book.BookService;
import validators.BookValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class BookAddNewController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfBookTitle, tfBookEdition, tfBookAuthor, tfYearOfPublication;
    @FXML
    private AnchorPane apAddNewBook;

    private BooksController booksController = BooksController.getInstance();
    private BookValidator bookValidator = new BookValidator();
    private BookService bookService = new BookService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            Book book = new Book(tfBookTitle.getText(), tfBookAuthor.getText(),
                    tfBookEdition.getText(), tfYearOfPublication.getText());
            if (bookValidator.checkAllTextField(book)) {
                bookService.saveBook(book);
                booksController.initTableData();
                getStage().close();
            }
        });
        btnCancel.setOnAction(event -> getStage().close());
    }

    private Stage getStage() {
        return ((Stage) apAddNewBook.getScene().getWindow());
    }


}
