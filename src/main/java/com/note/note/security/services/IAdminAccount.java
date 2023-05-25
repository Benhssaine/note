package com.note.note.security.services;

import com.note.note.models.Admin;
import com.note.note.security.models.Role;

import java.time.LocalDate;

public interface IAdminAccount {
    Admin addNewAdmin(String firstName, String lastName, LocalDate dob, String phone, String secretCode, String password, String confirmPassword) throws Exception;
    Role addNewRole(String roleName);
    Boolean addRoleToAdmin(String secretCode, String roleName);
    Boolean removeRoleFromAdmin(String secretCode, String roleName);
    Admin loadAdminBySecretCode(String secretCode);
}
