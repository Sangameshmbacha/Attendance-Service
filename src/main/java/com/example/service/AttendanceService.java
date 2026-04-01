package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.AttendanceRequestDTO;
import com.example.dto.AttendanceResponseDTO;
import com.example.entity.Attendance;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO dto) {

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(dto.getEmployeeId());
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());

        Attendance saved = repository.save(attendance);

        return mapToResponse(saved);
    }
    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO dto) {

        Attendance attendance = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));

        attendance.setEmployeeId(dto.getEmployeeId());
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());

        Attendance updated = repository.save(attendance);

        return mapToResponse(updated);
    }

    public List<AttendanceResponseDTO> getMonthlyAttendance(Long empId, int month, int year) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return repository.findByEmployeeIdAndDateBetween(empId, start, end)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AttendanceResponseDTO mapToResponse(Attendance attendance) {
        return AttendanceResponseDTO.builder()
                .id(attendance.getId())
                .employeeId(attendance.getEmployeeId())
                .date(attendance.getDate())
                .status(attendance.getStatus())
                .checkIn(attendance.getCheckIn())
                .checkOut(attendance.getCheckOut())
                .build();
    }
}