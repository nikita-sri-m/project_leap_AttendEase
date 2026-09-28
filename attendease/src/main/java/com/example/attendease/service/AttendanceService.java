package com.example.attendease.service;

import com.example.attendease.entity.Session;
import com.example.attendease.entity.Student;
import com.example.attendease.repository.AttendanceRepository;
import com.example.attendease.repository.SessionRepository;
import com.example.attendease.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private final StudentRepository studentRepository;
    private final SessionRepository sessionRepository;
    private final AttendanceRepository attendanceRepository;
    public AttendanceService(
            StudentRepository studentRepository,
            SessionRepository sessionRepository,
            AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.sessionRepository = sessionRepository;
        this.attendanceRepository = attendanceRepository;
    }
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }
}