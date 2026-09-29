package com.example.attendease.repository;

import com.example.attendease.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository
        extends JpaRepository<AttendanceRecord, Long> {
}