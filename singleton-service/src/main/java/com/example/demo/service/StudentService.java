package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Thread.currentThread;
import static java.util.Arrays.asList;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudentById(int regNo) throws InterruptedException {
        //Filter student by registration number
        Optional<Student> student = studentRepository.getStudentByRegNo(regNo);

        //get filtered student name
        String studentName = student.map(Student::getName).orElse(null);

        //Sleep current thread 10 seconds
        Thread.sleep(10000);
        log.info("Thread name: {}; service instance: {}; student regNo: {}; student name: {}", currentThread().getName(), this, regNo, studentName);

        return student;
    }
}
