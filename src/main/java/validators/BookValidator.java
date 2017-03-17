package validators;

import model.Book;

public class BookValidator {

    public boolean checkAllTextField(Book book) {
        return !(book.getTitle().isEmpty()
                || book.getAuthor().isEmpty()
                || book.getEdition().isEmpty()
                || book.getYearOfPublication().isEmpty());
    }
}
