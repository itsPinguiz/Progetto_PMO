package Calendar;

import Calendar.Calendar.Seasons;
import Calendar.Calendar.Weather;

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
}
