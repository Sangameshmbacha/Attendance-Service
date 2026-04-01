package com.example.entity;

import java.time.LocalDate;

import com.example.enums.LeaveStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
    name = "employee_leave"
)
@Data
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
}

