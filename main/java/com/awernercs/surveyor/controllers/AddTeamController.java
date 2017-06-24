package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Team;
import com.awernercs.surveyor.models.data.TeamDAO;
import com.awernercs.surveyor.models.data.TeamMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("team/addteam")
public class AddTeamController {

    @Autowired
    private TeamDAO teamDao;
    @Autowired
    private TeamMemberDAO teamMemberDao;
    private String[] teamMemberIds;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /team/addteam (Get)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayAddTeamForm(Model model) {
        model.addAttribute(new Team());
        model.addAttribute("teammembers", teamMemberDao.findAll());
        return "team/addteam";
    }

    // Request path: /team/addteam (Post)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddSurveyForm(@ModelAttribute @Valid Team newTeam,
                                       @RequestParam("teammemberids[]") String teamMemberIds[],
                                       Errors errors,
                                       Model model) {

        if (errors.hasErrors()) {
            return "team/addteam";
        }

        for (int i = 0; i < teamMemberIds.length; i++){
            newTeam.getTeamMembers().add(teamMemberDao.findOne(Integer.parseInt(teamMemberIds[i])));
        }

        teamDao.save(newTeam);
        return "redirect:/team/";
    }
}