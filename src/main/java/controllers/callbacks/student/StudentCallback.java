package controllers.callbacks.student;

import model.Student;

public interface StudentCallback {
    void onStudentAdded(Student student);
    void onStudentDeleted(Student student);
    void onStudentEdit(Student student);
    void onStudentAddedToBlacklist(Student student);
    void onStudentDeletedFromBlackList(Student student);

}
