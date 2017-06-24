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
@RequestMapping("team/{paramOne}")
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
    public String displayAddTeamForm(@PathVariable(value = "paramOne") String paramOne,
                                     Model model) {

        Team holderTeam = teamDao.findOne(Integer.parseInt(paramOne));

        model.addAttribute(holderTeam);
        model.addAttribute("allteammembers", teamMemberDao.findAll());
        model.addAttribute("chosenteammembers", holderTeam.getTeamMembers());

        return "team/editteam";
    }

    // Request path: /team/{team.id} (Post)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddSurveyForm(@PathVariable(value = "paramOne") String paramOne,
                                       @RequestParam("updateteammemberids[]") String updateTeamMemberIds[],
                                       @ModelAttribute Team updateTeam,
                                       Model model) {

        Team originalTeam = teamDao.findOne(Integer.parseInt(paramOne));
        originalTeam.setName(updateTeam.getName());

        updateTeam.getTeamMembers().clear();

        for (int i = 0; i < updateTeamMemberIds.length; i++){
            originalTeam.getTeamMembers().add(teamMemberDao.findOne(Integer.parseInt(updateTeamMemberIds[i])));
        }

        teamDao.save(originalTeam);

        return "redirect:";
    }
}