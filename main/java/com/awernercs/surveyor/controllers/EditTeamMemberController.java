package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.TeamMember;
import com.awernercs.surveyor.models.data.TeamMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("teammember/{paramOne}")
public class EditTeamMemberController {

    @Autowired
    private TeamMemberDAO teamMemberDao;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /teammember/{teammember.id} (Get)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayAddTeamForm(@PathVariable(value = "paramOne") String paramOne,
                                     Model model) {

        TeamMember holderTeamMember = teamMemberDao.findOne(Integer.parseInt(paramOne));
        model.addAttribute(holderTeamMember);

        return "teammember/editteammember";
    }

    // Request path: /teammember/{teammember.id} (Post)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddSurveyForm(@PathVariable(value = "paramOne") String paramOne,
                                       @ModelAttribute TeamMember updateTeamMember,
                                       Model model) {

        TeamMember originalTeamMember = teamMemberDao.findOne(Integer.parseInt(paramOne)); // Get the unmodified team member
        originalTeamMember.setName(updateTeamMember.getName()); // Update Name
        originalTeamMember.setEmail(updateTeamMember.getEmail()); // Update Email
        teamMemberDao.save(originalTeamMember);

        return "redirect:/teammember/";
    }
}