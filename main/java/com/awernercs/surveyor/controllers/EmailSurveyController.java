package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.data.SurveyDAO;
import com.awernercs.surveyor.models.data.TeamDAO;
import com.awernercs.surveyor.models.data.TeamMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("survey/email")
public class EmailSurveyController {

    @Autowired
    private SurveyDAO surveyDao;
    @Autowired
    private TeamDAO teamDao;
    @Autowired
    TeamMemberDAO teamMemberDao;

    private String[] surveyIds;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /survey/email
    @RequestMapping(value = "")
    public String ProcessEmailSurveyForm(@RequestParam("surveyIds[]") String surveyIds[],
                        Model model) {
        ArrayList<Survey> surveys = new ArrayList<>();

        for (String id : surveyIds) {
            surveys.add(surveyDao.findOne(Integer.parseInt(id)));
        }

        model.addAttribute("surveys", surveys);
        model.addAttribute("teams", teamDao.findAll());
        model.addAttribute("teamMembers", teamMemberDao.findAll());

        return "survey/email";
    }

    /**
     * The below method is not yet functional. Ultimately it will allow users to email surveys to people listed on
     * teams or to individual team members.
     */
    // Request path: /survey/email
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String ProcessEmailSurveyForm(@RequestParam("surveyIds[]") String surveyIds[],
                                         @RequestParam("teamIds[]") String teamIds[],
                                         @RequestParam("teamMemberIds[]") String teamMemberIds[],
                                         Model model) {

        ArrayList<Survey> surveys = new ArrayList<>();

        for (String id : surveyIds) {
            surveys.add(surveyDao.findOne(Integer.parseInt(id)));
        }

        model.addAttribute("surveys", surveys);
        model.addAttribute("teams", teamDao.findAll());
        model.addAttribute("teamMembers", teamMemberDao.findAll());

        return "survey/email";
    }
}