package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class DummyFilterApiService {

    public List<Reunion> locationFilter(List<Reunion> reunionList, String lieu){
        List<Reunion> toRemoveList = new ArrayList<>();
        for (Reunion reunion : reunionList) {
            if(!reunion.getLieu().equals(lieu)){
                toRemoveList.add(reunion);
            }
        }
        reunionList.removeAll(toRemoveList);
        return reunionList;
    }



}
