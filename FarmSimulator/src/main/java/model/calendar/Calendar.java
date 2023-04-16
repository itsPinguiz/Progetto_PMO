/********************
 * IMPORT AND PACKAGE
 *******************/

package model.calendar;

import java.lang.Math;
import java.util.ArrayList;

/****************
 * CALENDAR CLASS
 ***************/
public class Calendar implements CalendarInt {

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
    
    public static Calendar instance;
    private Seasons seasons;
    private Weather weather;
    private int     actualDay,
                    random,
                    cont;

    private ArrayList<Seasons> seasonList;
    private ArrayList<Weather> weatherList;

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

    public static Calendar getInstance() {
        if (instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    public void inc(){
        if(this.actualDay % 120 == 0)
            this.actualDay = 1 ;
        else
            this.actualDay++;
            
        verifySeason();
        randomWeather();
    }

    public int getDay(){
        return this.actualDay;
    }
    
    public Weather getWeather(){
        return this.weather;
    }
    public Seasons getSeason(){
        return this.seasons;
    }
    public void setWeather(Weather weather){
       this.weather = weather; 
    }
    public void setSeason(Seasons season){
        this.seasons = season;
    }

    private void verifySeason(){
        if(this.actualDay % 30 == 0){
            if(this.cont == 3)
                this.cont = 0;
            else
                this.cont++;
            setSeason(this.seasonList.get(cont)); 
        }
    }

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
