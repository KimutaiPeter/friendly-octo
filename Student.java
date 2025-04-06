import java.util.*;
import java.time.LocalDate;

class Student {
    String name;
    String id;
    Map<String, Integer> subjects = new LinkedHashMap<>();
    double average;
    String grade;
    String recommendation;

    public void calculateAverageAndGrade() {
        int total = 0;
        for (int score : subjects.values()) {
            total += score;
        }
        average = total / (double) subjects.size();
        grade = getGrade(average);
        recommendation = getRecommendation(grade);
    }

    private String getGrade(double avg) {
        if (avg >= 70) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else if (avg >= 40) return "D";
        else return "F";
    }

    private String getRecommendation(String grade) {
        switch (grade) {
            case "A": return "Excellent";
            case "B": return "Very Good";
            case "C": return "Good";
            case "D": return "Poor";
            default: return "Very Poor";
        }
    }

    public void displayReport() {
        System.out.println("\n=====================================");
        System.out.println("Report Card - " + LocalDate.now());
        System.out.println("Name: " + name);
        System.out.println("ID Number: " + id);
        System.out.println("Subjects & Scores:");
        for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.printf("Average Score: %.2f\n", average);
        System.out.println("Grade: " + grade);
        System.out.println("Recommendation: " + recommendation);
        System.out.println("=====================================\n");
    }
}
