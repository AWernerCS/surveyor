package com.awernercs.surveyor.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class TeamMember {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String email;

    @ManyToMany(mappedBy = "teamMembers")
    private List<Team> teams;

    public TeamMember (){ }

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Team> getTeams() { return teams; }
}
