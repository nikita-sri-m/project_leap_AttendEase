package com.example.attendease.service;

import com.example.attendease.entity.AttendanceRecord;
import com.example.attendease.entity.Session;
import com.example.attendease.entity.Student;
import com.example.attendease.entity.Subject;
import com.example.attendease.repository.AttendanceRepository;
import com.example.attendease.repository.SessionRepository;
import com.example.attendease.repository.StudentRepository;
import com.example.attendease.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final StudentRepository studentRepository;
    private final SessionRepository sessionRepository;
    private final AttendanceRepository attendanceRepository;
    private final SubjectRepository subjectRepository;

    public AttendanceService(
            StudentRepository studentRepository,
            SessionRepository sessionRepository,
            AttendanceRepository attendanceRepository,
            SubjectRepository subjectRepository) {

        this.studentRepository = studentRepository;
        this.sessionRepository = sessionRepository;
        this.attendanceRepository = attendanceRepository;
        this.subjectRepository = subjectRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    public AttendanceRecord markAttendance(
            AttendanceRecord attendanceRecord) {

        List<AttendanceRecord> existingRecords =
                attendanceRepository.findAll();

        for (AttendanceRecord record : existingRecords) {

            if (record.getStudent().getId().equals(
                    attendanceRecord.getStudent().getId())
                    &&
                    record.getSession().getId().equals(
                            attendanceRecord.getSession().getId())) {

                throw new RuntimeException(
                        "Attendance already marked for this student in this session");
            }
        }

        return attendanceRepository.save(attendanceRecord);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public List<AttendanceRecord> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<AttendanceRecord> getAttendanceByStudent(
            Long studentId) {

        return attendanceRepository.findAll()
                .stream()
                .filter(record ->
                        record.getStudent()
                                .getId()
                                .equals(studentId))
                .toList();
    }

    public double getAttendancePercentage(Long studentId) {

        List<AttendanceRecord> records = attendanceRepository.findAll();

        long total = records.stream()
                .filter(r -> r.getStudent() != null)
                .filter(r -> r.getStudent().getId().equals(studentId))
                .count();

        long present = records.stream()
                .filter(r -> r.getStudent() != null)
                .filter(r -> r.getStudent().getId().equals(studentId))
                .filter(AttendanceRecord::isPresent)
                .count();

        if (total == 0) {
            return 0;
        }

        return (present * 100.0) / total;
    }

    public AttendanceRecord updateAttendance(
            Long id,
            AttendanceRecord updatedRecord) {

        AttendanceRecord record =
                attendanceRepository.findById(id)
                        .orElseThrow();

        record.setPresent(updatedRecord.isPresent());

        return attendanceRepository.save(record);
    }

    public List<Student> getStudentsBelowThreshold(double threshold) {

        List<Student> students = studentRepository.findAll();

        return students.stream()
                .filter(student ->
                        getAttendancePercentage(student.getId()) < threshold)
                .toList();
    }
}