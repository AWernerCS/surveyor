package com.awernercs.surveyor.controllers;

import com.awernercs.surveyor.models.Survey;
import com.awernercs.surveyor.models.SurveyOption;
import com.awernercs.surveyor.models.Strawpoll;
import com.awernercs.surveyor.models.data.SurveyDAO;
import com.awernercs.surveyor.models.data.SurveyOptionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
                                       @RequestParam String[] surveyOptionValues,
                                       @RequestHeader(value="User-Agent") String userAgent,
                                       Errors errors,
                                       Model model) {

        if (errors.hasErrors()) {
            return "survey/add";
        }

        for (int i = 0; i < 10; i++){
            newSurvey.surveyOptions.set(i, new SurveyOption(surveyOptionValues[i]));
        }

        Strawpoll newStraw = new Strawpoll();
        newStraw.setTitle(newSurvey.getQuestion());
        newStraw.setOptions(surveyOptionValues);
        newStraw.setMulti(true);

        // Http Entity code: https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/HttpEntity.html
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        HttpEntity<Strawpoll> entity = new HttpEntity<Strawpoll>(newStraw, headers);

        RestTemplate restTemplate = new RestTemplate();
        Strawpoll secondStraw = restTemplate.postForObject("https://strawpoll.me/api/v2/polls", entity, Strawpoll.class);

        newSurvey.setIsOpen(true);
        newSurvey.setDateAdded();
        newSurvey.setStrawHolder(secondStraw);

        surveyDao.save(newSurvey);
        return "redirect:";
    }

}