/********************
 * IMPORT AND PACKAGE
 *******************/

package model.calendar;

import java.lang.Math;
import java.util.ArrayList;

/****************
 * CALENDAR CLASS
 ***************/
/**
 * Represents a Calendar with seasons and weather conditions.
 * This class is a singleton, which means it can have only one instance. It provides methods to increment the day, get current day, 
 * weather and season, and also set the weather and season. It also generates random weather based on the current season.
 */
public class Calendar implements CalendarInt {
    /**
     * Enum representing the seasons of the year.
     */
    public enum Seasons{
        WINTER("Winter"),
        SPRING("Spring"),
        SUMMER("Summer"),
        AUTUMN("Autumn");

        private String name;

        Seasons(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    /**
     * Enum representing the weather conditions.
     */
    public enum Weather{
        SUNNY("Sunny"),
        CLOUDY("Cloudy"),
        RAINY("Rainy"),
        SNOWY("Snowy");

        private String name;

        Weather(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    /**
     * Singleton instance of the Calendar class.
     */
    public static Calendar instance;

    /**
     * Fields representing the current season, weather, day, and some auxiliary fields.
     */
    private Seasons seasons;
    private Weather weather;
    private int     actualDay,
                    random,
                    cont;

    private ArrayList<Seasons> seasonList;
    private ArrayList<Weather> weatherList;

    /**
     * Private constructor for the singleton Calendar class.
     */
    private Calendar(){
        this.actualDay = 1;

        this.seasonList = new ArrayList<Seasons>();
        for(int i = 0; i < Seasons.values().length; i++){
            this.seasonList.add(Seasons.values()[i]);
        }

        this.weatherList = new ArrayList<Weather>();
        for(int i = 0; i < Weather.values().length; i++){
            this.weatherList.add(Weather.values()[i]);
        }

        this.cont = 0;
        this.seasons = this.seasonList.get(cont);
        randomWeather();
    }

    /**
     * Gets the instance of the Calendar class.
     *
     * @return the instance of the Calendar class.
     */
    public static Calendar getInstance() {
        if (instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    /**
     * Increments the current day and updates the season and weather.
     */
    public void inc(){
        if(this.actualDay % 120 == 0)
            this.actualDay = 1 ;
        else
            this.actualDay++;
            
        verifySeason();
        randomWeather();
    }

    /**
     * Gets the current day.
     *
     * @return the current day.
     */
    public int getDay(){
        return this.actualDay;
    }

    /**
     * Gets the current weather.
     *
     * @return the current weather.
     */
    public Weather getWeather(){
        return this.weather;
    }

    /**
     * Gets the current season.
     *
     * @return the current season.
     */
    public Seasons getSeason(){
        return this.seasons;
    }

    /**
     * Sets the current weather.
     *
     * @param weather the new weather.
     */
    public void setWeather(Weather weather){
       this.weather = weather; 
    }

    /**
     * Sets the current season.
     *
     * @param season the new season.
     */
    public void setSeason(Seasons season){
        this.seasons = season;
    }
    
    /**
     * Updates the season based on the current day.
     */
    private void verifySeason(){
        if(this.actualDay % 30 == 0){
            if(this.cont == 3)
                this.cont = 0;
            else
                this.cont++;
            setSeason(this.seasonList.get(cont)); 
        }
    }

    /**
     * Generates a random weather based on the current season.
     */
    private void randomWeather(){

        this.random = (int)((Math.random() * 10) + 1);

        switch(this.seasons){
            case WINTER:
                if(this.random < 6){
                    this.weather = Weather.RAINY;
                }
                else if(this.random < 8){
                    this.weather = Weather.SNOWY;
                }
                else if(this.random < 10){
                    this.weather = Weather.CLOUDY;
                }
                else
                this.weather = Weather.SUNNY;
            break;

            case AUTUMN:
                if(this.random < 5)
                    this.weather = Weather.CLOUDY;
                else if(this.random < 9)
                    this.weather = Weather.SUNNY;
                else
                    this.weather = Weather.RAINY;
            break;

            case SUMMER:
                if(this.random < 7)
                    this.weather = Weather.SUNNY;
                else if(this.random < 10)
                    this.weather = Weather.CLOUDY;
                else
                    this.weather = Weather.RAINY;
            break;
            case SPRING:
                if(this.random < 4)
                    this.weather = Weather.SUNNY;
                
                else if(this.random < 7)
                    this.weather = Weather.CLOUDY;
                
                else
                    this.weather = Weather.RAINY;
            break;

        }
        
    }

    
}
