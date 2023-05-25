package com.note.note.controllers;

import com.note.note.models.Group;
import com.note.note.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/group/")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping("addGroupForm")
    public String addGroupForm(Model model){
        return "group/addGroupForm";
    }

    @RequestMapping("addGroup")
    public String addGroup(@ModelAttribute("group") Group group, Model model){
        groupService.addGroup(group);
        return getAllGroups(model, 0);
    }

    @RequestMapping("deleteGroup/{id}")
    public String deleteGroupById(@PathVariable("id") Long id, Model model){
        groupService.deleteGroupById(id);
        return getAllGroups(model, 0);
    }

    @RequestMapping("updateGroupForm/{id}")
    public String updateGroupForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("group", groupService.getGroupById(id));
        return "group/updateGroupForm";
    }

    @RequestMapping("updateGroup")
    public String updateGroup(@ModelAttribute("group") Group group, Model model){
        groupService.updateGroup(group);
        return getAllGroups(model, 0);
    }

    @GetMapping(path = "getAllGroups")
    public String getAllGroups(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<Group> groupPage = groupService.getAllGroups(page, 4);
        model.addAttribute("groupList", groupPage.getContent());
        model.addAttribute("pages", new int[groupPage.getTotalPages()]);
        model.addAttribute("currentPage", page);

        return "group/getAllGroups";
    }
}
