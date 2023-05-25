package com.note.note.repositories;

import com.note.note.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByEmailIgnoreCaseContains(PageRequest pr, String input);
    User findByEmail(String email);
}
