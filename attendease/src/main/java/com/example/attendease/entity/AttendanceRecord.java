package com.example.attendease.entity;

import jakarta.persistence.*;

@Entity
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Session session;
    private boolean present;
    public AttendanceRecord() {
    }
    public Long getId() {
        return id;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
    public boolean isPresent() {
        return present;
    }
    public void setPresent(boolean present) {
        this.present = present;
    }
}