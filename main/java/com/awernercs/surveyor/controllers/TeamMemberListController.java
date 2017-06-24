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
    public String index(Model model) {
        model.addAttribute("teamMembers", teamMemberDao.findAll());
        return "teammember/index";
    }

    // Request path: /teammember (Post)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String index(@RequestParam("teamMemberIds[]") String teamMemberIds[],
                        Model model) {

        TeamMember deleteTeamMember;

        for (int i = 0; i < teamMemberIds.length; i++){
            deleteTeamMember = teamMemberDao.findOne(Integer.parseInt(teamMemberIds[i]));
            for(int j = 0; j < deleteTeamMember.getTeams().size(); j++){
                deleteTeamMember.getTeams().get(j).getTeamMembers().remove(deleteTeamMember);
            }
            teamMemberDao.delete(Integer.parseInt(teamMemberIds[i]));
        }

        model.addAttribute("teamMembers", teamMemberDao.findAll());

        return "teammember/index";
    }
}