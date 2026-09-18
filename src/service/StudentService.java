package service;

import model.Student;
import util.FileManager;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final String FILE_NAME = "data/students.txt";

    public boolean registerStudent(Student student) {
        List<String> students = FileManager.readFromFile(FILE_NAME);

        for (String line : students) {
            String[] data = line.split("\\|");

            if (data.length >= 3 && data[0].equals(student.getStudentId())) {
                return false;
            }

            if (data.length >= 3 && data[2].equalsIgnoreCase(student.getEmail())) {
                return false;
            }
        }

        FileManager.appendToFile(FILE_NAME, student.toString());
        return true;
    }

    public Student loginStudent(String email, String password) {
        List<String> students = FileManager.readFromFile(FILE_NAME);

        for (String line : students) {
            String[] data = line.split("\\|");

            if (data.length >= 4 &&
                data[2].equalsIgnoreCase(email) &&
                data[3].equals(password)) {

                return new Student(data[0], data[1], data[2], data[3]);
            }
        }

        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        List<String> students = FileManager.readFromFile(FILE_NAME);

        for (String line : students) {
            String[] data = line.split("\\|");

            if (data.length >= 4) {
                studentList.add(
                    new Student(data[0], data[1], data[2], data[3])
                );
            }
        }

        return studentList;
    }
}