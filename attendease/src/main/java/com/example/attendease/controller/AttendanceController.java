package com.example.attendease.controller;
import jakarta.validation.Valid;
import com.example.attendease.entity.AttendanceRecord;
import com.example.attendease.entity.Session;
import com.example.attendease.entity.Student;
import com.example.attendease.entity.Subject;
import com.example.attendease.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(
            AttendanceService attendanceService) {

        this.attendanceService = attendanceService;
    }

    @PostMapping("/students")
    public Student addStudent(
            @Valid @RequestBody Student student) {

        return attendanceService.addStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return attendanceService.getAllStudents();
    }

    @PostMapping("/subjects")
    public Subject addSubject(
            @RequestBody Subject subject) {

        return attendanceService.addSubject(subject);
    }

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects() {

        return attendanceService.getAllSubjects();
    }

    @PostMapping("/sessions")
    public Session addSession(
            @RequestBody Session session) {

        return attendanceService.addSession(session);
    }

    @GetMapping("/sessions")
    public List<Session> getAllSessions() {
        return attendanceService.getAllSessions();
    }

    @PostMapping("/attendance")
    public AttendanceRecord markAttendance(
            @RequestBody AttendanceRecord attendanceRecord) {

        return attendanceService.markAttendance(attendanceRecord);
    }

    @GetMapping("/attendance")
    public List<AttendanceRecord> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/attendance/student/{studentId}")
    public List<AttendanceRecord> getAttendanceByStudent(
            @PathVariable Long studentId) {

        return attendanceService.getAttendanceByStudent(studentId);
    }

    @GetMapping("/attendance/percentage/{studentId}")
    public double getAttendancePercentage(
            @PathVariable Long studentId) {

        return attendanceService.getAttendancePercentage(studentId);
    }

    @GetMapping("/attendance/below/{threshold}")
    public List<Student> getStudentsBelowThreshold(
            @PathVariable double threshold) {

        return attendanceService.getStudentsBelowThreshold(threshold);
    }

    @PutMapping("/attendance/{id}")
    public AttendanceRecord updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceRecord attendanceRecord) {

        return attendanceService.updateAttendance(
                id,
                attendanceRecord);
    }
}