package friendly_octo.storage;

import friendly_octo.model.*;

import java.io.*;
import java.util.*;

public class StorageService {
    private final File file;

    public StorageService(String filepath) {
        this.file = new File(filepath);
    }

    // Save all students to CSV
    public void saveAll(List<Student> students) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            // Header: name,id,Math,English,Science,ICT,History
            pw.println("Name,ID," + String.join(",", students.get(0).getSubjects().keySet()));
            for (Student s : students) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.getName()).append(",").append(s.getId());
                for (int score : s.getSubjects().values()) {
                    sb.append(",").append(score);
                }
                pw.println(sb);
            }
        }
    }

    // Load students from CSV (if exists)
    public List<Student> loadAll() throws IOException {
        List<Student> students = new ArrayList<>();
        if (!file.exists()) return students;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Student s = new Student();
                s.setName(parts[0]);
                s.setId(parts[1]);
                // subjects start at index 2
                for (int i = 2; i < parts.length; i++) {
                    String subject = header.split(",")[i];
                    int score = Integer.parseInt(parts[i]);
                    s.getSubjects().put(subject, score);
                }
                students.add(s);
            }
        }
        return students;
    }
}
