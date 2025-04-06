import java.util.*;
import java.time.LocalDate;

public class UniversityExamProcessor {

    static final int NUM_STUDENTS = 10;
    static final String[] SUBJECTS = {"Math", "English", "Science", "ICT", "History"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.println("=== University Examination System ===");

        for (int i = 1; i <= NUM_STUDENTS; i++) {
            Student student = new Student();
            System.out.println("\nEnter details for Student " + i);
            System.out.print("Name: ");
            student.name = sc.nextLine();
            System.out.print("ID Number: ");
            student.id = sc.nextLine();

            for (String subject : SUBJECTS) {
                int score;
                while (true) {
                    System.out.print("Enter score for " + subject + " (0-100): ");
                    try {
                        score = Integer.parseInt(sc.nextLine());
                        if (score >= 0 && score <= 100) break;
                        else System.out.println("Score must be between 0 and 100.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                }
                student.subjects.put(subject, score);
            }

            student.calculateAverageAndGrade();
            students.add(student);
        }

        // Display reports
        for (Student s : students) {
            s.displayReport();
        }

        sc.close();
    }
}