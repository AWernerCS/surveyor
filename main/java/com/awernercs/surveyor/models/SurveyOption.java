package com.awernercs.surveyor.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Amanda on 4/30/2017.
 */
@Entity
public class SurveyOption {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=200)
    private String optionText;

    public SurveyOption(String anOptionText) { this.optionText = anOptionText; }
    public SurveyOption() {}

    public int getId() { return id; }

    public String getOptionText() { return optionText; }
    public void setOptionText(String anOptionText) { this.optionText = anOptionText; }
}
