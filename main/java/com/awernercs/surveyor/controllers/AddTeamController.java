package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Team;
import com.awernercs.surveyor.models.data.TeamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
@RequestMapping("survey/addteam")
public class AddTeamController {

    @Autowired
    private TeamDAO teamDao;
    private String[] teamMemberIds;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /survey/addteam (Get)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayAddTeamForm(Model model) {
        model.addAttribute(new Team());
        return "survey/addteam";
    }

    // Request path: /survey/addteam (Post)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddSurveyForm(@ModelAttribute @Valid Team newTeam,
                                       Errors errors,
                                       Model model) {

        if (errors.hasErrors()) {
            return "survey/addteam";
        }

        teamDao.save(newTeam);
        return "redirect:";
    }
}