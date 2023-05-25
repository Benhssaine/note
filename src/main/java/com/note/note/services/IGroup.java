package com.note.note.services;

import com.note.note.models.Group;
import org.springframework.data.domain.Page;

public interface IGroup {
    Group addGroup(Group group);
    void deleteGroupById(Long id);
    void updateGroup(Group group);
    Group getGroupById(Long id);
    Page<Group> getAllGroups(int page, int size);
}
