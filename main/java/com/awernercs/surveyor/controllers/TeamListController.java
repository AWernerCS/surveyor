package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Team;
import com.awernercs.surveyor.models.TeamMember;
import com.awernercs.surveyor.models.data.TeamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("team")
public class TeamListController {

    @Autowired
    private TeamDAO teamDao;
    private String[] teamIds;
    private Errors errors;
    private Model model;

    // Request path: /team
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("teams", teamDao.findAll());
        return "team/index";
    }

    // Request path: /team (Post)
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=deleteteams")
    public String index(@RequestParam("teamIds[]") String teamIds[],
                        Model model) {
        for (int i = 0; i < teamIds.length; i++){ teamDao.delete(Integer.parseInt(teamIds[i])); }
        model.addAttribute("teams", teamDao.findAll());
        return "team/index";
    }

    // Request path: /teammember (Post)
    // Add Team Member Button
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=addteam")
    public String TeamMemberListAdd(Model model) {
        return "redirect:/team/addteam";
    }
}