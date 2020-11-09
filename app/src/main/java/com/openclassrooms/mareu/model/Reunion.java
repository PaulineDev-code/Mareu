package com.openclassrooms.mareu.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Reunion {

    private long id ;
    private String color;
    private String name ;
    private String aboutIt;
    private Date date ;
    private String room ;
    private String email ;

    public Reunion (long id, String color, String name, Date date, String room, String email, String aboutIt){

        this.id = id;
        this.color = color;
        this.name = name;
        this.aboutIt = aboutIt;
        this.date = date;
        this.room = room;
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

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }
    public void setRoom(String lieu) {
        this.room = lieu;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

//    //Room model
//    public class Room {
//        private String roomName;
//        private int color;
//
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(name, reunion.name);
    }



}

