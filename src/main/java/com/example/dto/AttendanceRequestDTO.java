package com.example.dto;
import java.time.LocalDate;
import java.time.LocalTime;

import com.example.enums.AttendanceStatus;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AttendanceRequestDTO {

    @NotNull
    private Long employeeId;

    @NotNull
    private LocalDate date;

    @NotNull
    private AttendanceStatus status;

    private LocalTime checkIn;
    private LocalTime checkOut;
}