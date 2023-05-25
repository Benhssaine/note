package com.note.note.security.repositories;

import com.note.note.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminAccountRepository extends JpaRepository<Admin, Long> {
    Admin findBySecretCode(String secretCode);
}
