package com.awernercs.surveyor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Survey {
    // This ID
    @Id
    @GeneratedValue
    @JsonProperty ("ignore") // This ID is used strictly by Surveyor, StrawPoll's API should ignore it.
    private int id;

    @NotNull
    @Size(min=2, max=400, message="Your question must be between 2 and 400 characters.") // 400 = StrawPoll's max question length.
    @JsonProperty ("title") // Used by StrawPoll
    private String question;
    private Date dateAdded;
    private boolean isArchived = false; // Since StrawPoll's API does not allow deletion, we will archive instead.
    private Date dateArchived;

    // Below properties are all used by Strawpoll
    @JsonProperty("id")
    private String spID;

    @NotNull
    @Size(min=2, max=10, message="You should have between 2 and 10 survey options.")
    @JsonProperty("options")
    private String[] spOptions = new String[10];

    @JsonProperty("multi")
    private boolean spMulti = false;

    @JsonProperty("dupcheck")
    private String spDupCheck = "";

    @JsonProperty("captcha")
    private boolean captcha = false;

    // This property will be used on the overview page at a later time.
    @JsonProperty("votes")
    private int[] spVotes = new int[10];

    public Survey(String question, String[] spOptions) {
        this.question = question;
        this.spOptions = spOptions;
    }

    public Survey() { }

    public long getId() { return id; }

    public Date getDateAdded() { return dateAdded; }
    public void setDateAdded() { this.dateAdded = new Date(); }

    public String getQuestion() { return question; }
    public void setQuestion(String aQuestion){ this.question = aQuestion; }

    public boolean getIsArchived() { return isArchived; }
    public void setIsArchived( boolean anIsArchived ) { this.isArchived = anIsArchived; }

    public Date getDateArchived() { return dateArchived; }
    public void setDateArchived() { this.dateArchived = new Date(); }

    public String getSpID() { return spID; }
    public void setSpID(String spID) { this.spID = spID; }

    public String[] getSpOptions() { return spOptions; }
    public void setSpOptions(String[] spOptions) { this.spOptions = spOptions; }

    public int[] getSpVotes() { return spVotes; }
    public void setSpVotes(int[] spVotes) {this.spVotes = spVotes; }

    public boolean isSpMulti() { return spMulti; }
    public void setSpMulti(boolean spMulti) { this.spMulti = spMulti; }

    public String getSpDupCheck() { return spDupCheck; }
    public void setSpDupCheck(String spDupCheck) { this.spDupCheck = spDupCheck; }

    public boolean isCaptcha() { return captcha; }
    public void setCaptcha(boolean captcha) { this.captcha = captcha; }

    // Used to create a Strawpoll via strawpoll's REST API.
    public void createStrawpoll() {
        // Strawpoll's API requires a user-agent and produces an error without one.
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Survey> request = new HttpEntity<Survey>(Survey.this, headers);

        Survey responsePoll = restTemplate.postForObject("https://www.strawpoll.me/api/v2/polls", request, Survey.class);
        spID = responsePoll.getSpID();
    }
}