package com.note.note.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work")
    private Boolean isWork;

    @Column(name = "discussion")
    private Boolean isDiscussion;

    @Column(name = "help")
    private Boolean isHelp;

    public Category(Boolean isWork, Boolean isDiscussion, Boolean isHelp){
        this.isWork = isWork;
        this.isDiscussion = isDiscussion;
        this.isHelp = isHelp;
    }

    public String getCategoryName() {
        if(this.isWork) {
            return "work";
        } else if(this.isDiscussion) {
            return "discussion";
        } else if(this.isHelp) {
            return "help";
        } else {
            return "undefined";
        }
    }

}
