package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.AttendanceRequestDTO;
import com.example.dto.AttendanceResponseDTO;
import com.example.service.AttendanceService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> mark(@Valid @RequestBody AttendanceRequestDTO dto) {
        return ResponseEntity.ok(service.markAttendance(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceRequestDTO dto) {

        return ResponseEntity.ok(service.updateAttendance(id, dto));
    }
    @GetMapping("/monthly")
    public ResponseEntity<List<AttendanceResponseDTO>> getMonthly(
            @RequestParam Long empId,
            @RequestParam int month,
            @RequestParam int year) {

        return ResponseEntity.ok(service.getMonthlyAttendance(empId, month, year));
    }
}