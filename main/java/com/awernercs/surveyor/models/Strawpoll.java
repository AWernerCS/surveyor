package com.awernercs.surveyor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Amanda on 5/4/2017.
 */
@JsonIgnoreProperties
public class Strawpoll {

    private long id;
    private String title;
    private String[] options;
    private boolean multi;
    private String dupcheck;
    private boolean captcha;

    public Strawpoll() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String[] getOptions() { return options; }
    public void setOptions(String[] options) { this.options = options; }

    public boolean isMulti() { return multi; }
    public void setMulti(boolean multi) { this.multi = multi; }

    public String getDupcheck() { return dupcheck; }
    public void setDupcheck(String dupcheck) { this.dupcheck = dupcheck; }

    public boolean isCaptcha() { return captcha; }
    public void setCaptcha(boolean captcha) { this.captcha = captcha; }

}
