package com.note.note.security.services;

import com.note.note.models.User;
import com.note.note.security.models.Role;

import java.time.LocalDate;

public interface IUserAccount {
    User addNewUser(String firstName, String lastName, LocalDate dob, String email, String password, String confirmPassword) throws Exception;
    Role addNewRole(String roleName);
    Boolean addRoleToUser(String email, String roleName);
    Boolean removeRoleFromUser(String email, String roleName);
    User loadUserByEmail(String email);
}
