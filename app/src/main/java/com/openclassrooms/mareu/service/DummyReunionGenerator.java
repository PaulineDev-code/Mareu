package com.openclassrooms.mareu.service;

import android.widget.DatePicker;

import com.example.mareu.R;
import com.openclassrooms.mareu.model.Reunion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DummyReunionGenerator {


    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(

            new Reunion(1, R.color.colorRed, "DummyReunion1", new Date(1609331400L*1000),
                    "Mario",  "parcipant1@gmail.com, participant2@gmail.com", "Première réunion auto-générée"),
            new Reunion(2, R.color.colorGreen, "DummyReunion2", new Date(1609419600L*1000),
                    "Luigi",  "parcipant1@gmail.com, participant2@gmail.com", "Deuxième réunion auto-générée"),
            new Reunion(3, R.color.colorYellow, "DummyReunion3", new Date(1609507800L*1000),
                    "Wario",  "parcipant1@gmail.com, participant2@gmail.com", "Troisième réunion auto-générée"),
            new Reunion(4, R.color.colorPurple, "DummyReunion4", new Date(1609596000L*1000),
                    "Waluigi",  "parcipant1@gmail.com, participant2@gmail.com", "Quatrième réunion auto-générée"),
            new Reunion(5, R.color.colorGreyWhite, "DummyReunion5", new Date(1609684200L*1000),
                    "Boo",  "parcipant1@gmail.com, participant2@gmail.com", "Cinquième réunion auto-générée")

            );

    public static List<Reunion> generateReunions() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }

}
