package com.awernercs.surveyor.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Amanda on 4/30/2017.
 */
@Entity
public class Survey {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String question;

    private Date dateAdded;

    private boolean isOpen;

    private String strawPollLink;

    //@NotNull
    //@Size(min=1, max=10)
    private HashMap<String, Integer> options = new HashMap<String, Integer>();

    public Survey(String question) {
        this.question = question;
        System.out.print("In the Survey Class adding a survey.");
    }

    public Survey() {}

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

    public HashMap<String, Integer> getOptions() { return options; }
    public void setOptions(HashMap<String, Integer> anOptionList) {this.options = anOptionList; }
}