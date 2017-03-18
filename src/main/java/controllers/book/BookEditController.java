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

public class BookEditController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfBookTitle, tfBookEdition, tfBookAuthor, tfYearOfPublication;
    @FXML
    private AnchorPane apBookEdit;

    private BookValidator bookValidator = new BookValidator();
    private BookService bookService = new BookService();
    private BooksController booksController = BooksController.getInstance();
    private Book book;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            if (!(tfBookTitle.getText().isEmpty()
                    || tfBookAuthor.getText().isEmpty()
                    || tfBookEdition.getText().isEmpty()
                    || tfYearOfPublication.getText().isEmpty())) {
                book.setTitle(tfBookTitle.getText());
                book.setAuthor(tfBookAuthor.getText());
                book.setEdition(tfBookEdition.getText());
                book.setYearOfPublication(tfYearOfPublication.getText());
                if (bookValidator.checkAllTextField(book)) {
                    bookService.updateBook(book);
                    booksController.initTableData();
                    getStage().close();
                }
            }
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    public void initBookInfo(Book book) {
        this.book = book;
        tfBookTitle.setText(book.getTitle());
        tfBookAuthor.setText(book.getAuthor());
        tfBookEdition.setText(book.getEdition());
        tfYearOfPublication.setText(book.getYearOfPublication());
    }

    private Stage getStage() {
        return ((Stage) apBookEdit.getScene().getWindow());
    }
}
