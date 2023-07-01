/********************
 * IMPORT AND PACKAGE
 *******************/

 package model.calendar;

 import java.io.Serializable;
 
 import model.calendar.Calendar.Seasons;
 import model.calendar.Calendar.Weather;
 
 /**
  * Interface representing a calendar with various seasons and weather conditions.
  * <p>
  * Provides methods to get and increment the day, get and set the weather, and get and set the season.
  */
 public interface CalendarInt extends Serializable{
     
     /**
      * Get the current day.
      * 
      * @return the current day.
      */
     int getDay();
     
     /**
      * Increment the current day.
      */
     void inc();
 
     /**
      * Get the current weather.
      * 
      * @return the current weather.
      */
     Weather getWeather();
 
     /**
      * Set the weather.
      * 
      * @param weather the weather to set.
      */
     void setWeather(Weather weather);
 
     /**
      * Set the season.
      * 
      * @param season the season to set.
      */
     void setSeason(Seasons season);
 
     /**
      * Get the current season.
      * 
      * @return the current season.
      */
     Seasons getSeason();
 }
 
