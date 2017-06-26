package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.TeamMember;
import com.awernercs.surveyor.models.data.TeamMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("teammember")
public class TeamMemberListController {

    @Autowired
    private TeamMemberDAO teamMemberDao;
    private String[] teamMemberIds;
    private Errors errors;
    private Model model;

    // Request path: /teammember
    @RequestMapping(value = "")
    public String TeamMemberList(Model model) {
        model.addAttribute("teamMembers", teamMemberDao.findAll());
        return "teammember/index";
    }

    // Request path: /teammember (Post)
    // Delete Team Members
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=deleteteammembers")
    public String TeamMemberListDelete(@RequestParam("teamMemberIds[]") String teamMemberIds[],
                        Model model) {
        for (int i = 0; i < teamMemberIds.length; i++){ teamMemberDao.delete(Integer.parseInt(teamMemberIds[i])); }
        model.addAttribute("teamMembers", teamMemberDao.findAll());
        return "teammember/index";
    }

    // Request path: /teammember (Post)
    // Add Team Member Button
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=addteammember")
    public String TeamMemberListAdd(Model model) {
        return "redirect:/teammember/addteammember";
    }
}