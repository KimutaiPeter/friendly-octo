package exam.processing.system.app;

import exam.processing.system.model.Student;
import exam.processing.system.storage.StorageService;

import java.io.IOException;
import java.util.*;

public class App {
    static final int NUM_STUDENTS = 10;
    static final String[] SUBJECTS = {"Math", "English", "Science", "ICT", "History"};
    static final String DATA_FILE = "students.csv";

    public static void main(String[] args) throws IOException {
        StorageService storage = new StorageService(DATA_FILE);
        List<Student> students = storage.loadAll();

        Scanner sc = new Scanner(System.in);
        if (students.isEmpty()) {
            System.out.println("=== Enter details for " + NUM_STUDENTS + " students ===");
            for (int i = 1; i <= NUM_STUDENTS; i++) {
                Student student = new Student();
                System.out.println("\nStudent " + i);
                System.out.print("Name: ");
                student.setName(sc.nextLine());
                System.out.print("ID: ");
                student.setId(sc.nextLine());
                for (String subject : SUBJECTS) {
                    int score;
                    while (true) {
                        System.out.print(subject + " score (0-100): ");
                        try {
                            score = Integer.parseInt(sc.nextLine());
                            if (score < 0 || score > 100)
                                throw new NumberFormatException();
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid. Enter 0–100.");
                        }
                    }
                    student.getSubjects().put(subject, score);
                }
                student.calculateAverageAndGrade();
                students.add(student);
            }
            storage.saveAll(students);
            System.out.println("\nData saved to " + DATA_FILE);
        } else {
            System.out.println("=== Loaded " + students.size() + " students from " + DATA_FILE + " ===");
        }

        // Display all reports
        for (Student s : students) {
            s.displayReport();
        }
        sc.close();
    }
}
