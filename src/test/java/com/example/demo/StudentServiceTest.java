package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest // Loads the Spring context for testing
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testLoadStudentsFromCSV() {

        List<Student> students = studentService.getAllStudents();

        assertEquals(3, students.size()); // Expect 2 students
        assertEquals(1L, students.get(0).getId());
        assertEquals("Alice Johnson", students.get(0).getName());

    }

    @Test
    public void testGetStudentById() {
        Student student = studentService.getStudentById(1L);
        assertNotNull(student);
        assertEquals("Alice", student.getName());
    }

    // Add more tests for error cases, edge cases, etc.
}

