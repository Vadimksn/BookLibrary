package controllers.callbacks.student;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentObservable {
    private static List<StudentCallback> studentCallbacks = new ArrayList<>();

    public static void registerStudentCallback(StudentCallback studentCallback){
        studentCallbacks.add(studentCallback);
    }

    public static void onStudentAdded(Student student){
        for (StudentCallback studentCallback : studentCallbacks) {
            studentCallback.onStudentAdded(student);
        }
    }

    public static void onStudentDeleted(Student student){
        for (StudentCallback studentCallback : studentCallbacks) {
            studentCallback.onStudentDeleted(student);
        }
    }

    public static void onStudentEdit(Student student){
        for (StudentCallback studentCallback : studentCallbacks) {
            studentCallback.onStudentEdit(student);
        }
    }

    public static void onStudentAddedToBlacklist(Student student){
        for (StudentCallback studentCallback : studentCallbacks) {
            studentCallback.onStudentAddedToBlacklist(student);
        }
    }

    public static void onStudentDeletedFromBlackList(Student student){
        for (StudentCallback bookCallback : studentCallbacks) {
            bookCallback.onStudentDeletedFromBlackList(student);
        }
    }
}
