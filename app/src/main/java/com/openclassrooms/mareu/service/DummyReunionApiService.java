package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyReunionApiService implements  ReunionApiService {

    private List<Reunion> listReunion = new ArrayList<>();


    /**
     * {@inheritDoc}
     */

    @Override
    public List<Reunion> getReunion() {
        return listReunion;
    }




    @Override
    public void deleteReunion(Reunion reunion) { listReunion.remove(reunion); }




    @Override
    public void createReunion(Reunion reunion) {
        listReunion.add(reunion);
    }

    @Override
    public Reunion getReunionbyid(long id) {
        for (Reunion reunion : listReunion) {
            if( reunion.getId()==id){
                return reunion;
            }
        }
        return null;
    }
}