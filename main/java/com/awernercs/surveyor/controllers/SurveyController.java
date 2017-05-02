package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.SurveyOption;
import com.awernercs.surveyor.models.data.SurveyDAO;
import com.awernercs.surveyor.models.data.SurveyOptionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
                                       @RequestParam String surveyOptionOne,
                                       Model model) {
        SurveyOption option = new SurveyOption(surveyOptionOne);
        surveyOptionDao.save(option);

        if (errors.hasErrors()) {
            return "survey/add";
        }

        newSurvey.setIsOpen(true);
        newSurvey.setDateAdded();
        newSurvey.addSurveyOption(surveyOptionDao.findOne(option.getId()));

        surveyDao.save(newSurvey);
        return "redirect:";
    }

}