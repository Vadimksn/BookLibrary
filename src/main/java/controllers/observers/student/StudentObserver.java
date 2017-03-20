package controllers.observers.student;

import model.Student;

public interface StudentObserver {
    void onStudentAdded(Student student);
    void onStudentDeleted(Student student);
    void onStudentEdit(Student student);
    void onStudentAddedToBlacklist(Student student);
    void onStudentDeletedFromBlackList(Student student);

}
