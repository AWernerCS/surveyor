package com.awernercs.surveyor.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

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


    public Survey(String question) {
        this.question = question;
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
}