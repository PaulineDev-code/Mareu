package com.example.mareu;

import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Reunion;
import com.openclassrooms.mareu.service.DummyReunionGenerator;
import com.openclassrooms.mareu.service.ReunionApiService;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UnitTest {
    private ReunionApiService service = DI.getNewInstanceApiService();

    private Reunion reunionTest = new Reunion(11, R.color.colorRed, "ReunionTest", new Date(1609507800L*1000),
            "Mario",  "parcipant1@gmail.com", "Une réunion de test");

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = service.getReunion();
        List<Reunion> expectedReunions = DummyReunionGenerator.DUMMY_REUNIONS;
        assertEquals(reunions, expectedReunions);
    }


    @Test
    public void createReunionWithSuccess() {
        service.createReunion(reunionTest);
        List<Reunion> reunions = service.getReunion();
        assertTrue(reunions.contains(reunionTest));
    }

    @Test
    public void deleteReunionWithSuccess() {
        service.getReunion();
        Reunion reunionToDelete = service.getReunion().get(2);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunion().contains(reunionToDelete));
    }

    @Test
    public void filterRoomWithSuccess() {
        service.getReunion();
        List<Reunion> listReunions = service.filterRoom("Luigi");
        for (Reunion reunion : listReunions) {
            assertSame("Luigi", reunion.getRoom());
        }
    }

    @Test
    public void filterDateWithSuccess() {
        service.getReunion();
        Date date = new Date(1609507800L*1000);
        List<Reunion> listReunions = service.dateFilter(date);
        for (Reunion reunion : listReunions) {
            assertEquals(date, reunion.getDate());
        }

    }
}
