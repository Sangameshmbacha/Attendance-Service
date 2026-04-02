package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.LeaveRequestDTO;
import com.example.dto.LeaveResponseDTO;
import com.example.entity.Attendance;
import com.example.entity.Leave;
import com.example.enums.AttendanceStatus;
import com.example.enums.LeaveStatus;
import com.example.repository.AttendanceRepository;
import com.example.repository.LeaveRepository;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository repository;

    @Autowired
    private AttendanceRepository attendanceRepository; 
    public LeaveResponseDTO applyLeave(LeaveRequestDTO dto) {

        Leave leave = new Leave();
        leave.setEmployeeId(dto.getEmployeeId());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus(LeaveStatus.PENDING);

        Leave saved = repository.save(leave);

        return mapToResponse(saved);
    }

    public LeaveResponseDTO approveLeave(Long id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));

        leave.setStatus(LeaveStatus.APPROVED);
        LocalDate current = leave.getStartDate();
        while (!current.isAfter(leave.getEndDate())) {

            Attendance attendance = new Attendance();
            attendance.setEmployeeId(leave.getEmployeeId());
            attendance.setDate(current);
            attendance.setStatus(AttendanceStatus.LEAVE);

            attendanceRepository.save(attendance);
            current = current.plusDays(1);
        }

        Leave updated = repository.save(leave);

        return mapToResponse(updated);
    }

    public LeaveResponseDTO rejectLeave(Long id) {

        Leave leave = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));

        leave.setStatus(LeaveStatus.REJECTED);

        Leave updated = repository.save(leave);

        return mapToResponse(updated);
    }

    public List<LeaveResponseDTO> getAllLeaves() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private LeaveResponseDTO mapToResponse(Leave leave) {
        return LeaveResponseDTO.builder()
                .id(leave.getId())
                .employeeId(leave.getEmployeeId())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .build();
    }
}
