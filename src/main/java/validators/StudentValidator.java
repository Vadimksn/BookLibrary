package validators;

import model.Student;

/**
 * Created by Vadim on 05.03.2017.
 */
public class StudentValidator {

    public boolean checkAllTextField(Student student) {
        return !(student.getName().isEmpty()
                || student.getSurname().isEmpty()
                || student.getMiddleName().isEmpty()
                || student.getPassportData().isEmpty());
    }
}
