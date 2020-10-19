package com.openclassrooms.mareu.service;


import com.openclassrooms.mareu.model.Reunion;

import java.util.List;


/**
 * Reunion API client
 */
public interface ReunionApiService {


    List<Reunion> getReunion();


    void deleteReunion(Reunion reunion);


    void createReunion(Reunion reunion);
    Reunion getReunionbyid(long id);
}