package com.openclassrooms.mareu.events;

import com.openclassrooms.mareu.model.Reunion;

public class DeleteReunionEvent {

    public Reunion reunion;

    public DeleteReunionEvent (Reunion reunion){
        this.reunion = reunion;
    }
}
