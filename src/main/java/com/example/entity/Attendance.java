
package com.example.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.enums.AttendanceStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
    name = "attendance",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employeeId", "date"})
    }
)
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private LocalTime checkIn;
    private LocalTime checkOut;
}