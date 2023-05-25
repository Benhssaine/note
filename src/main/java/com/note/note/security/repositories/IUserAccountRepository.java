package com.note.note.security.repositories;

import com.note.note.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAccountRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
