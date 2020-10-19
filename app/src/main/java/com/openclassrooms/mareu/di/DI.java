package com.openclassrooms.mareu.di;

import com.openclassrooms.mareu.service.DummyReunionApiService;
import com.openclassrooms.mareu.service.ReunionApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static ReunionApiService service = new DummyReunionApiService();


    public static ReunionApiService getReunionApiService() {
        return service;
    }


    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
