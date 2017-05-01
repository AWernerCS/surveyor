package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.SurveyDAO.SurveyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Amanda on 4/30/2017.
 */
@Controller
@RequestMapping("survey")
public class SurveyController {

    @Autowired
    private SurveyDAO surveyDao;

    // Request path: /survey
    @RequestMapping(value = "")
    public String index(Model model) {
        System.out.print("In the Survey Controller controlling stuff.");
        model.addAttribute("surveys", surveyDao.findAll());
        return "survey/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute(new Survey());
        return "survey/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Survey newSurvey,
                                       Errors errors,
                                       Model model) {
        if (errors.hasErrors()) {
            return "survey/add";
        }

        newSurvey.setIsOpen(true);
        newSurvey.setDateAdded();

        surveyDao.save(newSurvey);
        return "redirect:";
    }

}