package controllers.observers.student;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentObservable {
    private static List<StudentObserver> studentObservers = new ArrayList<>();

    public static void registerStudentObserver(StudentObserver studentObserver){
        studentObservers.add(studentObserver);
    }

    public static void onStudentAdded(Student student){
        for (StudentObserver studentObserver : studentObservers) {
            studentObserver.onStudentAdded(student);
        }
    }

    public static void onStudentDeleted(Student student){
        for (StudentObserver studentObserver : studentObservers) {
            studentObserver.onStudentDeleted(student);
        }
    }

    public static void onStudentEdit(Student student){
        for (StudentObserver studentObserver : studentObservers) {
            studentObserver.onStudentEdit(student);
        }
    }

    public static void onStudentAddedToBlacklist(Student student){
        for (StudentObserver studentObserver : studentObservers) {
            studentObserver.onStudentAddedToBlacklist(student);
        }
    }

    public static void onStudentDeletedFromBlackList(Student student){
        for (StudentObserver bookCallback : studentObservers) {
            bookCallback.onStudentDeletedFromBlackList(student);
        }
    }
}
