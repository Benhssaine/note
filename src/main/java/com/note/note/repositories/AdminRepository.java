package com.note.note.repositories;

import com.note.note.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findBySecretCode(String secretCode);
}
