package com.note.note.security.services;

import com.note.note.models.User;
import com.note.note.security.models.Role;
import com.note.note.security.repositories.IUserAccountRepository;
import com.note.note.security.repositories.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class UserAccountService implements IUserAccount {

    private IUserAccountRepository accountRepository;
    private IRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public User addNewUser(String firstName, String lastName, LocalDate dob, String email, String password, String confirmPassword) throws Exception {
        if(accountRepository.findByEmail(email) != null)
            return null;
        if(! password.equals(confirmPassword))
            return null;
        User newUser = accountRepository.save(new User(firstName, lastName, dob, email, passwordEncoder.encode(password)));
        addRoleToUser(newUser.getEmail(),"USER");
        return newUser;
    }

    @Override
    public Role addNewRole(String roleName) {
        if(roleRepository.findByName(roleName) != null)
            return null;
        return roleRepository.save(new Role(roleName));
    }

    @Override
    public Boolean addRoleToUser(String email, String roleName) {
        User user = accountRepository.findByEmail(email);
        if(user == null) return false;

        Role role = roleRepository.findByName(roleName);
        if(role == null)
            role = addNewRole(roleName);

        if(user.getRoles() == null){
            user.setRoles(new ArrayList<Role>());
            user.getRoles().add(role);
            return true;
        }
        if(user.getRoles().contains(role)) return false;
        user.getRoles().add(role);
        return true;
    }

    @Override
    public Boolean removeRoleFromUser(String email, String roleName) {
        User user = accountRepository.findByEmail(email);
        if(user == null) return false;
        Role role = roleRepository.findByName(roleName);
        if(role == null) return false;
        user.getRoles().remove(role);
        return true;
    }

    @Override
    public User loadUserByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}

