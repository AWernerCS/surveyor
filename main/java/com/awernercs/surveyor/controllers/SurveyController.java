package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.SurveyOption;
import com.awernercs.surveyor.models.data.SurveyDAO;
import com.awernercs.surveyor.models.data.SurveyOptionDAO;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.CascadeType;
import javax.validation.Valid;

/**
 * Created by Amanda on 4/30/2017.
 */
@Controller
@RequestMapping("survey")
public class SurveyController {

    @Autowired
    private SurveyDAO surveyDao;

    @Autowired
    private SurveyOptionDAO surveyOptionDao;

    // Request path: /survey
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("surveys", surveyDao.findAll());
        return "survey/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddSurveyForm(Model model) {
        model.addAttribute(new Survey());
        return "survey/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSurveyForm(@ModelAttribute @Valid Survey newSurvey,
                                       Errors errors,
                                       @RequestParam String surveyOption0, @RequestParam String surveyOption1,
                                       @RequestParam String surveyOption2, @RequestParam String surveyOption3,
                                       @RequestParam String surveyOption4, @RequestParam String surveyOption5,
                                       @RequestParam String surveyOption6, @RequestParam String surveyOption7,
                                       @RequestParam String surveyOption8, @RequestParam String surveyOption9,
                                       Model model) {

        if (errors.hasErrors()) {
            return "survey/add";
        }

        newSurvey.setIsOpen(true);
        newSurvey.setDateAdded();

        // Set the new survey options.
        newSurvey.getSurveyOptions().get(0).setOptionText(surveyOption0);
        newSurvey.getSurveyOptions().get(1).setOptionText(surveyOption1);
        newSurvey.getSurveyOptions().get(2).setOptionText(surveyOption2);
        newSurvey.getSurveyOptions().get(3).setOptionText(surveyOption3);
        newSurvey.getSurveyOptions().get(4).setOptionText(surveyOption4);
        newSurvey.getSurveyOptions().get(5).setOptionText(surveyOption5);
        newSurvey.getSurveyOptions().get(6).setOptionText(surveyOption6);
        newSurvey.getSurveyOptions().get(7).setOptionText(surveyOption7);
        newSurvey.getSurveyOptions().get(8).setOptionText(surveyOption8);
        newSurvey.getSurveyOptions().get(9).setOptionText(surveyOption9);

        surveyDao.save(newSurvey);
        return "redirect:";
    }

}