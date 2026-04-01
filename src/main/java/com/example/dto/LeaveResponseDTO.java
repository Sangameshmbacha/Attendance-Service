package com.example.dto;

import java.time.LocalDate;

import com.example.enums.LeaveStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaveResponseDTO {

    private Long id;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;
}