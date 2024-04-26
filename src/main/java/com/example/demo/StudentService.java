package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    @Value("${students.csv.path}")
    private String csvPath;

    private Map<Long, Student> students = new HashMap<>();

    public void loadStudentsFromCSV() {
        try (FileReader reader = new FileReader(csvPath)) {
            List<Student> studentList = new CsvToBeanBuilder(reader)
                    .withType(Student.class)
                    .build().parse();

            for (Student student : studentList) {
                students.put(student.getId(), student);
                System.out.println(student.getName());
            }
        } catch (Exception e) {
            System.err.println("Error loading CSV: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {

        if (students.isEmpty()) {
            loadStudentsFromCSV();
        }

        return new ArrayList<Student>(students.values());
    }

    public Student getStudentById(Long id) {

        if (students.isEmpty()) {
            loadStudentsFromCSV();
        }

        return students.get(id);
    }
}


