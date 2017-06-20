package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.data.SurveyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

@Controller
@RequestMapping("survey/archivedsurveys")
public class ArchiveSurveyController {

    @Autowired
    private SurveyDAO surveyDao;
    private Model model;

    // Request path: /survey/archivedsurveys
    @RequestMapping(value = "")
    public String archivedSurveys(Model model) {

        Iterable<Survey> surveyList = surveyDao.findAll();
        ArrayList<Survey> archivedSurveys = new ArrayList<>();

        for (Survey survey : surveyList){
            if (survey.getIsArchived() == true){
                archivedSurveys.add(survey);
            }
        }

        model.addAttribute("surveys", archivedSurveys);
        return "survey/archivedsurveys";
    }

}