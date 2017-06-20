package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.data.SurveyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
@RequestMapping("survey/addsurvey")
public class AddSurveyController {

    @Autowired
    private SurveyDAO surveyDao;
    private String[] surveyIds;
    private BindingResult results;
    private Errors errors;
    private Model model;

    // Request path: /survey/addsurvey (Get)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayAddSurveyForm(Model model) {
        model.addAttribute(new Survey());
        return "survey/addsurvey";
    }

    // Request path: /survey/addsurvey (Post)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddSurveyForm(@ModelAttribute @Valid Survey newSurvey,
                                       @RequestParam("myTest[]") String surveyOptions[],
                                       Errors errors,
                                       Model model) {

        if (errors.hasErrors()) {
            return "survey/addsurvey";
        }

        newSurvey.setIsArchived(false);
        newSurvey.setDateAdded();
        newSurvey.setSpOptions(surveyOptions);

        newSurvey.createStrawpoll();

        surveyDao.save(newSurvey);
        return "redirect:";
    }
}