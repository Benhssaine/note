package com.note.note.services;

import com.note.note.models.Group;
import com.note.note.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    // Methods for add, delete, update, get group
    public Group addGroup (Group group){
        return groupRepository.save(group);
    }

    public void deleteGroupById(Long id){
        groupRepository.deleteById(id);
    }

    public Group updateGroup (Group group){
        return groupRepository.save(group);
    }

    public Group getGroupById(Long id){
        return groupRepository.findById(id).get();
    }

    public Page<Group> getAllGroups(int page, int size){
        return groupRepository.findAll(PageRequest.of(page, size));
    }

    // Methods for get all groups
    public Page<Group> getAllGroupsByUserId(Long userId, int page, int size){
        return groupRepository.findAllByUsersId(PageRequest.of(page, size), userId);
    }

    public Page<Group> findGroupByNameAndUserId(String name, Long userId, int page, int size){
        return groupRepository.findByNameIgnoreCaseContainsAndUsersId(PageRequest.of(page, size), name, userId);
    }

    public Page<Group> findGroupByName(String name, int page, int size){
        return groupRepository.findByNameIgnoreCaseContains(PageRequest.of(page, size), name);
    }
}
