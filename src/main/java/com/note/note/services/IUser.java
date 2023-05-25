package com.note.note.services;

import com.note.note.models.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUser {
    User addUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
    User getUserById(Long id);
    Page<User> getAllUsers(int page, int size);
    List<User> getAllUsers();
    Page<User> findUserByEmail(String email, int page, int size);
}
