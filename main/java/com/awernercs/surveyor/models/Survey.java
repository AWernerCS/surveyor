package com.awernercs.surveyor.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Amanda on 4/30/2017.
 */
@Entity
public class Survey {
    @Id
    @GeneratedValue
    private int id;

    // 400 = StrawPoll Max Length
    @NotNull
    @Size(min=1, max=400)
    private String question;

    private Date dateAdded;

    private boolean isOpen;

    private String strawPollLink;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "survey_id")
    public List<SurveyOption> surveyOptions = new ArrayList();

    public Survey(String question) {
        this.question = question;
        this.surveyOptionPop();
    }

    public Survey() {
        this.surveyOptionPop();
    }

    public int getId() { return id; }

    public Date getDateAdded() { return dateAdded; }
    public void setDateAdded() { this.dateAdded = new Date(); }

    public String getStrawPollLink() { return strawPollLink; }
    public void setStrawPollLink() {
        // insert StrawPoll API Stuff Here
    }

    public String getQuestion() { return question; }
    public void setQuestion(String aQuestion){ this.question = aQuestion; }

    public boolean getIsOpen() { return isOpen; }
    public void setIsOpen( boolean anIsOpen ) { this.isOpen = anIsOpen; }

    public List<SurveyOption> getSurveyOptions() { return surveyOptions; }
    public void setSurveyOptions( List<SurveyOption> newSurveyOptions ) { this.surveyOptions = newSurveyOptions; }

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
}