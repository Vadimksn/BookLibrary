package utils.ui;

public interface UiConstants {

    interface Tittle {
        String START_TITTLE = "БІБЛІОТЕКА";
        String STUDENT_CHOOSE_TITTLE = "Оберіть студента";
        String STUDENT_INFO_TITTLE = "Інформація читача";
        String STUDENT_ADD_NEW_TITTLE = "Додати нового читача";
        String BOOK_EDIT_TITTLE = "Змінити книгу";
        String BOOK_ADD_NEW_TITTLE = "Додати нову книгу";
        String ERROR = "Помилка";
        String CONFIRMATION = "Підтвердження";
    }

    interface Dialogs {
        String PASSPORT_DATA_ERROR = "Читач з такими паспортними даними вже існує!";
        String STUDENT_DELETE_ERROR = "Читач не здав книгу!";
        String CONFIRMATION_QUESTION = "Ви впевнені?";

    }

    interface Path {
        String START_PATH = "fxml/start_view.fxml";
        String STUDENT_CHOOSE_PATH = "fxml/student/student_choose_view.fxml";
        String STUDENT_INFO_PATH = "fxml/student/student_info_view.fxml";
        String STUDENT_ADD_NEW_PATH = "fxml/student/student_add_new_view.fxml";
        String BOOK_EDIT_PATH = "fxml/book/book_edit_view.fxml";
        String BOOK_ADD_NEW_PATH = "fxml/book/book_add_new_view.fxml";
    }
}
