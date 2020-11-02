package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Reunion;

import java.util.List;

public interface FilterApiService {

    List<Reunion> locationFilter (List<Reunion> reunionList, String lieu);

    List<Reunion> dateFilter(List<Reunion> reunionList, String heure);

}
