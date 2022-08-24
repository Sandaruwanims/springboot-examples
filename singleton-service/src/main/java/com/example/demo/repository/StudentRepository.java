package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Repository
public class StudentRepository {

    private static final List<Student> studentList = asList(
            new Student(1001, "Harry"),
            new Student(1002, "Hermione"),
            new Student(1003, "Ron")
    );

    public Optional<Student> getStudentByRegNo(int regNo) {
        //Filter student by registration number
        return studentList.stream()
                .filter(s -> s.getRegNo() == regNo)
                .findFirst();
    }
}
