/********************
 * IMPORT AND PACKAGE
 *******************/

package Calendar;

import Calendar.Calendar.Seasons;
import Calendar.Calendar.Weather;

/********************
 * CALENDAR INTERFACE
 *******************/
public interface CalendarInt {
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
