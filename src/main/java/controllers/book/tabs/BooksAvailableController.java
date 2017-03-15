package controllers.book.tabs;

import controllers.BaseTableController;
import controllers.student.StudentChooseController;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 15.03.2017.
 */
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
    private ObservableList<Book> bookObservableList;
    private BookService bookService = new BookService();

    public static BooksAvailableController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initTableData();
        setButtonGiveBookListener();
        setTextFieldFindBookListener();
    }

    private void setButtonGiveBookListener() {
        btnGiveBook.setOnAction(event -> {
            if (getSelectionItem() != null && getSelectionItem().isAvailable()) {
                Stage stage = new Stage();
                Parent root = null;
                FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("fxml/student/student_choose_view.fxml"));
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

    private void setTextFieldFindBookListener() {
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String stringForSearch = tfSearch.getText();

            if (stringForSearch.isEmpty()) {
                tvBooks.setItems(bookObservableList);
            } else {
                List<Book> bookListByString = new ArrayList<>();
                for (Book book : bookObservableList) {
                    if (book.toStringForSearch().contains(stringForSearch))
                        bookListByString.add(book);
                }
                ObservableList<Book> newList = FXCollections.observableArrayList(bookListByString);
                tvBooks.setItems(newList);
            }
        });
    }

    @Override
    public void initTableData() {
        bookObservableList = FXCollections.observableArrayList(bookService.getAvailableBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("yearOfPublication"));
        tvBooks.setItems(bookObservableList);
        tvBooks.setVisible(true);
    }

    @Override
    public Book getSelectionItem() {
        int id = getSelectedId();
        if (id != -1) {
            return bookObservableList.get(id);
        } else return null;
    }


    private int getSelectedId() {
        return tvBooks.getSelectionModel().getSelectedIndex();
    }
}
