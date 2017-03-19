package utils.ui;

import controllers.BaseTableController;
import controllers.book.BookEditController;
import controllers.student.StudentChooseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;

public class ViewUtil {


    public static FXMLLoader showView(String viewPath, String viewTitle) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(ViewUtil.class.getClassLoader().getResource(viewPath));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(viewTitle);
        stage.setScene(new Scene(root));
        stage.show();
        return loader;
    }

    public static void showStudentChoose(Book book) {
        FXMLLoader loader = showView(UiPathConstants.STUDENT_CHOOSE_PATH, UiTitleConstants.STUDENT_CHOOSE_TITTLE);
        StudentChooseController studentChooseController = loader.getController();
        studentChooseController.setBook(book);

    }

    public static void showBookEditView(BaseTableController<Book> baseTableController) {
        FXMLLoader loader = showView(UiPathConstants.BOOK_EDIT_PATH, UiTitleConstants.BOOK_EDIT_TITTLE);
        BookEditController bookEditController = loader.getController();
        bookEditController.initBookInfo(baseTableController.getSelectionItem());
    }


}
