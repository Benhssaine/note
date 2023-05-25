package com.note.note.security.services;

import com.note.note.models.Admin;
import com.note.note.models.User;
import com.note.note.security.models.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServicePerso implements UserDetailsService {
    private UserAccountService accountService;
    private AdminAccountService adminAccountService;
    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user = accountService.loadUserByEmail(input);
        if(user == null){
            Admin admin = adminAccountService.loadAdminBySecretCode(input);
            if(admin == null)
                throw new UsernameNotFoundException(String.format("User with  '%s' not found.", input));
            else{
                String [] roleList = admin.getRoles().stream()
                        .map(Role::getName)
                        .toArray(String[]:: new);

                UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(admin.getSecretCode())
                        .password(admin.getPassword())
                        .roles(roleList)
                        .build();

                return userDetails;
            }
        }
        else{
            String [] roleList = user.getRoles().stream()
                    .map(Role::getName)
                    .toArray(String[]::new);

            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(roleList)
                    .build();

            return userDetails;
        }
    }
}
