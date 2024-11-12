package com.edu.fateczl.teamplayer.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Adriano M Sanchez
 */
public class Player {

    private Integer id;
    private String name;
    private LocalDate birthdate;
    private Float height;
    private Float weight;
    private Team team;

    public Player(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringBuffer()
                .append(id).append(". ")
                .append(name)
                .append(" H:").append(height).append("m ")
                .append("W:").append(weight).append("kg ")
                .append("Team:").append(team.getName()).toString();
    }

}
