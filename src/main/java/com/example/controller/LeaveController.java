package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.LeaveRequestDTO;
import com.example.dto.LeaveResponseDTO;

import com.example.service.LeaveService;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService service;

    @PostMapping("/apply")
    public ResponseEntity<LeaveResponseDTO> applyLeave(@RequestBody LeaveRequestDTO dto) {
        return ResponseEntity.ok(service.applyLeave(dto));
    }
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveResponseDTO> approveLeave(@PathVariable Long id) {
        return ResponseEntity.ok(service.approveLeave(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(@PathVariable Long id) {
        return ResponseEntity.ok(service.rejectLeave(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaveResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllLeaves());
    }
}