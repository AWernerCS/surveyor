package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.data.SurveyDAO;
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
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String index(@RequestParam("surveyIds[]") String surveyIds[],
                        Model model) {
        model.addAttribute("surveys", surveyDao.findAll());

        for (int i = 0; i < surveyIds.length; i++){
            Survey holderSurvey = surveyDao.findOne(Integer.parseInt(surveyIds[i]));
            holderSurvey.setIsArchived(true);
            holderSurvey.setDateArchived();
            surveyDao.save(holderSurvey);
        }

        return "survey/index";
    }
}