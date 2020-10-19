package com.openclassrooms.mareu.model;

import java.util.Objects;

public class Reunion {

    private long id ;
    private String color;
    private String name ;
    private String aboutIt;
    private String heure ;
    private String lieu ;
    private String email ;

    public Reunion (long id, String color, String name, String heure, String lieu, String email, String aboutIt){

        this.id = id;
        this.color = color;
        this.name = name;
        this.aboutIt = aboutIt;
        this.heure = heure;
        this.lieu = lieu;
        this.email = email;

    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) {this.color = color; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAboutIt() { return aboutIt; }
    public void setAboutIt(String aboutIt) { this.aboutIt = aboutIt; }

    public String getHeure() {
        return heure;
    }
    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(name, reunion.name);
    }



}
