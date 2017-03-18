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
    private String viewTitle;
    private FXMLLoader loader;

    public ViewUtil(String viewPath, String viewTitle) {
        this.viewTitle = viewTitle;
        loader = new FXMLLoader(getClass().getClassLoader().getResource(viewPath));
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void showView() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(viewTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void showStudentChoose(BaseTableController<Book> baseTableController){
        ViewUtil viewUtil = new ViewUtil("fxml/student/student_choose_view.fxml","Оберіть студента");
        viewUtil.showView();
        StudentChooseController studentChooseController = viewUtil.getLoader().getController();
        studentChooseController.setBaseTableController(baseTableController);
    }
    public static void showBookEditView(BaseTableController<Book> baseTableController){
        ViewUtil viewUtil = new ViewUtil("fxml/book/book_edit_view.fxml", "Змінити книгу");
        viewUtil.showView();
        BookEditController bookEditController = viewUtil.getLoader().getController();
        bookEditController.initBookInfo(baseTableController.getSelectionItem());
    }


}
