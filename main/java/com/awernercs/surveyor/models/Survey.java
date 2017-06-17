package com.awernercs.surveyor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Amanda on 4/30/2017.
 */
@Entity
public class Survey {
    @Id
    @GeneratedValue
    @JsonProperty ("ignore")
    private long id;

    // 400 = StrawPoll Max Length
    @NotNull
    @Size(min=1, max=400)
    @JsonProperty ("title") // Used by Strawpoll
    private String question;
    private Date dateAdded;
    private boolean isOpen;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "survey_id")
    public List<SurveyOption> surveyOptions = new ArrayList();

    @JsonProperty("id") // Used by Strawpoll
    private String spID;

    // Below properties are all used by Strawpoll
    // Some values hard-coded for now, more will be added in final version
    @JsonProperty("options")
    private String[] spOptions = new String[10];
    @JsonProperty("votes")
    private int[] spVotes = new int[10];
    @JsonProperty("multi")
    private boolean spMulti = false;
    @JsonProperty("dupcheck")
    private String spDupcheck = "normal";
    @JsonProperty("captcha")
    private boolean captcha = false;

    public Survey(String question) {
        this.question = question;
        this.surveyOptionPop();
    }

    public Survey() {
        this.surveyOptionPop();
    }

    public long getId() { return id; }

    public Date getDateAdded() { return dateAdded; }
    public void setDateAdded() { this.dateAdded = new Date(); }

    public String getQuestion() { return question; }
    public void setQuestion(String aQuestion){ this.question = aQuestion; }

    public boolean getIsOpen() { return isOpen; }
    public void setIsOpen( boolean anIsOpen ) { this.isOpen = anIsOpen; }

    public List<SurveyOption> getSurveyOptions() { return surveyOptions; }
    public void setSurveyOptions( List<SurveyOption> newSurveyOptions ) { this.surveyOptions = newSurveyOptions; }

    public String getSpID() { return spID; }
    public void setSpID(String spID) { this.spID = spID; }

    public String[] getSpOptions() { return spOptions; }
    public void setSpOptions(String[] spOptions) { this.spOptions = spOptions; }

    public int[] getSpVotes() { return spVotes; }
    public void setSpVotes(int[] spVotes) {this.spVotes = spVotes; }

    public boolean isSpMulti() { return spMulti; }
    public void setSpMulti(boolean spMulti) { this.spMulti = spMulti; }

    public String getSpDupcheck() { return spDupcheck; }
    public void setSpDupcheck(String spDupcheck) { this.spDupcheck = spDupcheck; }

    public boolean isCaptcha() { return captcha; }
    public void setCaptcha(boolean captcha) { this.captcha = captcha; }

    public void addSurveyOption(SurveyOption option) {
        this.surveyOptions.add(option);
    }

    // Populates the Survey Option List with 10 blank options. This helps for
    // looping through the list in the HTML code.
    private void surveyOptionPop() {
        for (int i = 0; i < 10; i++){
            surveyOptions.add(new SurveyOption());
        }
    }

    public void createStrawpoll() {

        for (int i = 0; i < surveyOptions.size(); i++){
            spOptions[i] = surveyOptions.get(i).getOptionText();
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<Survey> request = new HttpEntity<Survey>(Survey.this, headers);

        Survey responsePoll = restTemplate.postForObject("https://www.strawpoll.me/api/v2/polls", request, Survey.class);
        System.out.println("The ID is: " + responsePoll.getSpID());
        spID = responsePoll.getSpID(); // test
    }
}