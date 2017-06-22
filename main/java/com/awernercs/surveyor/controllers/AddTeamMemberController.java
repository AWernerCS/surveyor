package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.TeamMember;
import com.awernercs.surveyor.models.data.TeamMemberDAO;
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
@RequestMapping("teammember/addteammember")
public class AddTeamMemberController {

    @Autowired
    private TeamMemberDAO tmDao;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /teammember/addteammember
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayAddTeamMemberForm(Model model) {
        model.addAttribute(new TeamMember());
        return "teammember/addteammember";
    }

    // Request path: /teammember/addteammember (Post)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddSurveyForm(@ModelAttribute @Valid TeamMember newTeamMember,
                                       Errors errors,
                                       Model model) {

        if (errors.hasErrors()) {
            return "teammember/addteammember";
        }

        tmDao.save(newTeamMember);
        return "redirect:";
    }

}
