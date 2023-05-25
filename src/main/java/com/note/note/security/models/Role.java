package com.note.note.security.models;

import com.note.note.models.Admin;
import com.note.note.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Roles")
@Data @AllArgsConstructor @NoArgsConstructor
public class Role{
    @Id
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="admins_roles",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> admins;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Role(String name){
        this.name = name;
    }
}
