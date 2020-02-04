package com.example.springbootjpa.dao;

import com.example.springbootjpa.dto.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByUserName(String userName);
    Staff findByUserNameOrEmail(String username, String email);
    Page<Staff> findAll(Pageable pageable);
}