package com.example.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.enums.AttendanceStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceResponseDTO {

    private Long id;
    private Long employeeId;
    private LocalDate date;
    private AttendanceStatus status;
    private LocalTime checkIn;
    private LocalTime checkOut;
}