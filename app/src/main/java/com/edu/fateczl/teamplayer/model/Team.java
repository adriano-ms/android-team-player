package com.edu.fateczl.teamplayer.model;

import java.util.Objects;

/**
 * @author Adriano M Sanchez
 */
public class Team {

    private Integer code;
    private String name;
    private String city;

    public Team(){
        super();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append(code).append(". ").append(name)
                .append(" | City: ").append(city).toString();
    }
}
