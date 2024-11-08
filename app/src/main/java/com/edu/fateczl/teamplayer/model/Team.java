package com.edu.fateczl.teamplayer.model;

/**
 * @author Adriano M Sanchez
 */
public class Team {

    private int code;
    private String name;
    private String city;

    public Team(){
        super();
    }

    public int getCode() {
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
        return "Team{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
