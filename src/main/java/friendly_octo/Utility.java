package friendly_octo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import friendly_octo.model.Student;
import friendly_octo.storage.StorageService;

public class Utility {
    static final int NUM_STUDENTS = 10;
    static final String[] SUBJECTS = { "Math", "English", "Science", "ICT", "History" };
    static final String DATA_FILE = "students.csv";

    public void view_all_reports() throws IOException {
        StorageService storage = new StorageService(DATA_FILE);
        List<Student> students = storage.loadAll();

        if (students.isEmpty()) {
            System.out.println("=== Please Enter details for atleast " + NUM_STUDENTS + " students ===");

        } else {
            System.out.println("=== Loaded " + students.size() + " students from " + DATA_FILE + " ===");
        }

        // Display all reports
        for (Student s : students) {
            s.displayReport();
        }
        System.out.println("Press any key to continue");
        System.in.read();

    }

    public void enter_marks() throws IOException {
        StorageService storage = new StorageService(DATA_FILE);
        List<Student> students = storage.loadAll();

        Scanner sc = new Scanner(System.in);

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

        // Display all reports
        for (Student s : students) {
            s.displayReport();
        }
        System.out.println("Press any key to continue");
        System.in.read();
    }

    public void view_my_report() throws IOException {
        StorageService storage = new StorageService(DATA_FILE);
        List<Student> students = storage.loadAll();

        
        System.out.println("Enter your admin number:");
        
        Scanner sd = new Scanner(System.in).useDelimiter("\\A");
        String Admin = sd.nextLine();

        // Display all reports
        if (students.isEmpty()) {
            System.out.println("Not found");
        } else {
            
            for (Student s : students) {
                String id = s.getId();
                if (id.equals(Admin)) {
                    s.displayReport();
                }
            }
        }

        System.out.println("Press any key to continue");
        

    }

}
