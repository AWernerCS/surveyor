package com.awernercs.surveyor.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import org.apache.commons.validator.routines.EmailValidator;

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

    public Boolean isValidEmail(String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        return valid;
    }
}
