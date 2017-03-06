package validators;

import model.Book;

/**
 * Created by Vadim on 05.03.2017.
 */
public class BookValidator {

    public boolean checkAllTextField(Book book) {
        return !(book.getTitle().isEmpty()
                || book.getAuthor().isEmpty()
                || book.getEdition().isEmpty()
                || book.getYearOfPublication().isEmpty());
    }
}
