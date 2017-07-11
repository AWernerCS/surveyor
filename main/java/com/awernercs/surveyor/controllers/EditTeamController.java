package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.Team;
import com.awernercs.surveyor.models.data.TeamDAO;
import com.awernercs.surveyor.models.data.TeamMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("team/{paramTeamID}")
public class EditTeamController {

    @Autowired
    private TeamDAO teamDao;
    @Autowired
    private TeamMemberDAO teamMemberDao;
    private String[] teamMemberIds;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /team/{team.id} (Get)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayEditTeamForm(@PathVariable(value = "paramTeamID") String paramTeamID,
                                      Model model) {

        Team holderTeam = teamDao.findOne(Integer.parseInt(paramTeamID));

        model.addAttribute(holderTeam);
        model.addAttribute("allteammembers", teamMemberDao.findAll());
        model.addAttribute("chosenteammembers", holderTeam.getTeamMembers());

        return "team/editteam";
    }

    // Request path: /team/{team.id} (Post)
    // Save Team/Edit Functionality
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=save")
    public String processEditTeamFormSave (@PathVariable(value = "paramTeamID") String paramTeamID,
                                      @RequestParam("updateteammemberids[]") String updateTeamMemberIds[],
                                      @ModelAttribute Team updateTeam,
                                      Model model) {

        Team originalTeam = teamDao.findOne(Integer.parseInt(paramTeamID)); // Get the unmodified team
        originalTeam.setName(updateTeam.getName()); // Modify the team's name
        originalTeam.getTeamMembers().clear(); // Clear the existing members

        // Add the members chosen on the edit screen
        for (int i = 0; i < updateTeamMemberIds.length; i++){
            originalTeam.getTeamMembers().add(teamMemberDao.findOne(Integer.parseInt(updateTeamMemberIds[i])));
        }

        teamDao.save(originalTeam);
        return "redirect:/team/";
    }

    // Request path: /team/{team.id} (Post)
    // Delete Team Functionality
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=delete")
    public String processEditTeamFormDelete (@PathVariable(value = "paramTeamID") String paramTeamID,
                                      @ModelAttribute Team updateTeam,
                                      Model model) {
        teamDao.delete(teamDao.findOne(Integer.parseInt(paramTeamID)));
        return "redirect:/team/";
    }

    // Request path: /team/{team.id} (Post)
    // Abort/Cancel Functionality (returns to team list)
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=cancel")
    public String processEditTeamFormCancel (Model model) {
        return "redirect:/team/";
    }
}