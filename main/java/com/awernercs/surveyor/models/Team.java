package com.awernercs.surveyor.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany // One team may have many team members.
    private List<TeamMember> teamMembers = new ArrayList<>();

    private String name;

    public int getId() { return id; }

    public List<TeamMember> getTeamMembers() { return teamMembers; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // A simple function to check if a team has a certain team member. Useful
    // for deletion and managing lists.
    public boolean hasTeamMember(int id) {

        boolean hasTeamMemberVar = false;

        for (int i = 0; i < teamMembers.size(); i++){
            if (teamMembers.get(i).getId() == id){
                hasTeamMemberVar = true;
                break;
            }
        }

        return hasTeamMemberVar;
    }
}
