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
@RequestMapping("survey")
public class SurveyController {

    @Autowired
    private SurveyDAO surveyDao;
    @Autowired
    private TeamDAO teamDao;
    @Autowired
    private TeamMemberDAO teamMemberDao;

    private String[] surveyIds;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /survey
    @RequestMapping(value = "")
    public String index(Model model) {

        Iterable<Survey> surveyList = surveyDao.findAll();
        ArrayList<Survey> unArchivedSurveys = new ArrayList<>();

        for (Survey survey : surveyList){
            if (survey.getIsArchived() != true){
                unArchivedSurveys.add(survey);
            }
        }

        model.addAttribute("surveys", unArchivedSurveys);
        return "survey/index";
    }

    // Request path: /survey (Post)
    // Archive surveys (hide them from the main list)
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=archive")
    public String ProcessSurveyListArchive(@RequestParam("surveyIds[]") String surveyIds[],
                        Model model) {

        // Archive all selected surveys.
        for (int i = 0; i < surveyIds.length; i++){
            Survey holderSurvey = surveyDao.findOne(Integer.parseInt(surveyIds[i]));
            holderSurvey.setIsArchived(true);
            holderSurvey.setDateArchived();
            surveyDao.save(holderSurvey);
        }

        Iterable<Survey> surveyList = surveyDao.findAll();
        ArrayList<Survey> unArchivedSurveys = new ArrayList<>();

        for (Survey survey : surveyList){
            if (survey.getIsArchived() != true){
                unArchivedSurveys.add(survey);
            }
        }

        model.addAttribute("surveys", unArchivedSurveys);
        return "survey/index";
    }

    // Request path: /survey (Post)
    // Email Surveys (launches default email client)
    @RequestMapping(value = "", method = RequestMethod.POST, params="action=email")
    public String ProcessSurveyListEmail(@RequestParam("surveyIds[]") String surveyIds[],
                        Model model) {

        ArrayList<Survey> surveys = new ArrayList<>();
        for (String surveyId : surveyIds){
            surveys.add(surveyDao.findOne(Integer.parseInt(surveyId)));
        }

        model.addAttribute("surveys", surveys);
        model.addAttribute("teams", teamDao.findAll());
        model.addAttribute("teammembers", teamMemberDao.findAll());

        return "survey/email";
    }
}