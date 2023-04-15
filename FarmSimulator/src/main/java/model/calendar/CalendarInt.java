/********************
 * IMPORT AND PACKAGE
 *******************/

package model.calendar;

import java.io.Serializable;

import model.calendar.Calendar.Seasons;
import model.calendar.Calendar.Weather;

/********************
 * CALENDAR INTERFACE
 *******************/
public interface CalendarInt extends Serializable{
    /**
     * Methods for days
     */
    int  getDay();
    void inc();

    /**
     * Methods for weather
     */
    Weather getWeather();
    void    setWeather(Weather weather);

    /**
     * Methods for seasons
     */
    void    setSeason(Seasons season);
    Seasons getSeason();

    /*
     * Method for an instance of calendar
     */

    /*Calendar getInstance();*/

}
