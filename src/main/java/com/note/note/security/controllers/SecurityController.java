package com.note.note.security.controllers;

import com.note.note.models.Admin;
import com.note.note.models.User;
import com.note.note.security.services.AdminAccountService;
import com.note.note.security.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class SecurityController {
    private UserAccountService accountService;
    private AdminAccountService adminAccountService;

    @GetMapping("/notAuthorized")
    public String noAuthorized(){
        return "security/notAuthorized";
    }
    @GetMapping("/login")
    public String loginForm(){
        return "security/login";
    }
    @GetMapping("/register")
    public String registerForm(){
        return "security/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam(name="confirmPassword") String confirmPassword, Model model) throws Exception {
        User newUser = accountService.addNewUser(user.getFirstName(), user.getLastName(), user.getDob(), user.getEmail(), user.getPassword(), confirmPassword);
        if(newUser == null){
            model.addAttribute("errorMessage", "Email already exists, or passwords don't match...");
            return "security/register";
        }

        model.addAttribute("welcomeMessage", "Account created successfully ! Please log in.");
        return "security/login";
    }

    @GetMapping("/admin/register")
    public String adminRegisterForm(){
        return "security/registerAdmin";
    }

    @PostMapping("/admin/register")
    public String adminRegister(@ModelAttribute(name="admin") Admin admin, @RequestParam(name="confirmPassword") String confirmPassword, Model model) throws Exception {
        Admin newAdmin = adminAccountService.addNewAdmin(admin.getFirstName(), admin.getLastName(), admin.getDob(), admin.getPhone(), admin.getSecretCode(), admin.getPassword(), confirmPassword);
        if(newAdmin == null){
            model.addAttribute("errorMessage", "Secret code already exists, or passwords don't match...");
            return "security/registerAdmin";
        }
        model.addAttribute("welcomeMessage", "Account created successfully ! Please log in.");
        return "security/loginAdmin";
    }

    @GetMapping("/admin/login")
    public String loginAdminForm(){
        return "security/loginAdmin";
    }
}
