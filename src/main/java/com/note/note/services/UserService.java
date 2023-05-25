package com.note.note.services;

import com.note.note.models.User;
import com.note.note.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public Page<User> getAllUsers(int page, int size){
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Page<User> findUserByEmail(String email, int page, int size){
        return userRepository.findByEmailIgnoreCaseContains(PageRequest.of(page, size), email);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
