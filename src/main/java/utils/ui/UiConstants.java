package utils.ui;

public interface UiConstants {

    interface Tittle {
        String START_TITTLE = "БІБЛІОТЕКА - version 1.0 [by vadimksn]";
        String STUDENT_CHOOSE_TITTLE = "Оберіть студента";
        String STUDENT_INFO_TITTLE = "Інформація читача";
        String STUDENT_ADD_NEW_TITTLE = "Додати нового читача";
        String BOOK_EDIT_TITTLE = "Змінити книгу";
        String BOOK_ADD_NEW_TITTLE = "Додати нову книгу";
        String ERROR = "Помилка";
        String CONFIRMATION = "Підтвердження";
        String RELAX = "~ Relax, дєтка ~";
    }

    interface Dialogs {
        String PASSPORT_DATA_ERROR = "Читач з такими паспортними даними вже існує!";
        String STUDENT_BOOK_ERROR = "Читач не здав книгу!";
        String CONFIRMATION_QUESTION = "Ви впевнені?";

    }

    interface Path {
        String RELAX = "fxml/relax/gandalf.fxml";
        String GANDALF = "mp3/relax/gandalf_mix.mp3";
        String VADIMKSN = "png/vadimksn.png";
        String START_PATH = "fxml/start_view.fxml";
        String STUDENT_CHOOSE_PATH = "fxml/student/student_choose_view.fxml";
        String STUDENT_INFO_PATH = "fxml/student/student_info_view.fxml";
        String STUDENT_ADD_NEW_PATH = "fxml/student/student_add_new_view.fxml";
        String BOOK_EDIT_PATH = "fxml/book/book_edit_view.fxml";
        String BOOK_ADD_NEW_PATH = "fxml/book/book_add_new_view.fxml";
    }
}
