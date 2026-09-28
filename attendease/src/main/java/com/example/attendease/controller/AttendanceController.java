package com.example.attendease.controller;

import com.example.attendease.entity.Session;
import com.example.attendease.entity.Student;
import com.example.attendease.service.AttendanceService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return attendanceService.addStudent(student);
    }
    @PostMapping("/sessions")
    public Session addSession(@RequestBody Session session) {
        return attendanceService.addSession(session);
    }
}