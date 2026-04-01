package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Leave;


public interface LeaveRepository extends JpaRepository<Leave, Long> {
}