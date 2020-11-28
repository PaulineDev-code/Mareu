package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Calendar.DAY_OF_MONTH;

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
    public List<Reunion> filterRoom(String filteredRoom){

        List<Reunion> roomList = new ArrayList<>();
            for (Reunion reunion : listReunion) {
                if(reunion.getRoom().equals(filteredRoom)){

                    roomList.add(reunion);

                }
            }
            return roomList;
        }

        public List<Reunion> dateFilter(Date filteredDate){

            List<Reunion> dateList = new ArrayList<>();
            for (Reunion reunion : listReunion) {
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();

                cal1.setTime(filteredDate);
                cal2.setTime(reunion.getDate());

                boolean sameDate = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                                   cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                                   cal1.get(DAY_OF_MONTH) == cal2.get(DAY_OF_MONTH);


                if(sameDate){

                    dateList.add(reunion);

                }
            }
            return dateList;

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

