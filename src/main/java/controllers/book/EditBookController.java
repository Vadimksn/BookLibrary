package controllers.book;

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

/**
 * Created by Vadim on 14.03.2017.
 */
public class EditBookController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfBookTitle, tfBookEdition, tfBookAuthor, tfYearOfPublication;
    @FXML
    private AnchorPane apAddNewBook;

    private BookValidator bookValidator = new BookValidator();
    private BookService bookService = new BookService();
    private Book book;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBtnOkListener();
        setBtnCancelListener();
    }

    private void setBtnOkListener() {
        btnOk.setOnAction(event -> {
            book.setTitle(tfBookTitle.getText());
            book.setAuthor(tfBookAuthor.getText());
            book.setEdition(tfBookEdition.getText());
            book.setYearOfPublication(tfYearOfPublication.getText());
            if (bookValidator.checkAllTextField(book)) {
                bookService.updateBook(book);
                getStage().close();
            }
        });
    }

    private void setBtnCancelListener() {
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
        return ((Stage) apAddNewBook.getScene().getWindow());
    }
}
