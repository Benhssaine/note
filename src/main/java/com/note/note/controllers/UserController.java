package com.note.note.controllers;

import com.note.note.models.User;
import com.note.note.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/admin/user/addUserForm")
    public String addUserForm(){
        return "user/addUserForm";
    }

    @RequestMapping("/admin/user/addUser")
    public String addUser(@ModelAttribute("user") User user, Model model){
        userService.addUser(user);
        return getAllUsers(0, model);
    }

    @RequestMapping("/admin/user/deleteUser/{id}")
    public String deleteUserById(@PathVariable("id") Long id, Model model){
        userService.deleteUserById(id);
        return getAllUsers(0, model);
    }

    @RequestMapping("/admin/user/updateUserForm/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user/updateUserForm";
    }

    @RequestMapping("/admin/user/updateUser")
    public String updateUser(@ModelAttribute("user") User user, Model model){
        userService.updateUser(user);
        return getAllUsers(0, model);
    }

    @RequestMapping("/admin/user/getUser/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user/getUser";
    }

    @RequestMapping("/admin/user/getAllUsers")
    public String getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                              Model model){
        Page<User> pageUser = userService.getAllUsers(page, 4);
        model.addAttribute("userList", pageUser.getContent());
        model.addAttribute("pages", new int[pageUser.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "user/getAllUsers";
    }

    @RequestMapping("/admin/user/getUsersFound")
    public String getUsersFound(@ModelAttribute("name") String name,
                                @RequestParam(name="page", defaultValue = "0") int page,
                                Model model){
        if(name.isEmpty() || name.equals(" "))
            return getAllUsers(0, model);

        Page<User> userPage = userService.findUserByEmail(name, page, 4);

        if(page > userPage.getTotalPages()){
            --page;
            userPage = userService.findUserByEmail(name, page, 4);
            if(userPage.getContent().isEmpty())
                return getAllUsers(0, model);
        }

        model.addAttribute("userList", userPage.getContent());
        model.addAttribute("pages", new int[userPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        return "/user/getUsersFound";
    }
}
