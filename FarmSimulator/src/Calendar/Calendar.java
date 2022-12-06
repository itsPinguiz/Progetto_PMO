package Calendar;

import java.lang.Math;

public class Calendar implements CalendarInt {

    public enum Seasons{
        WINTER, SPRING, SUMMER, AUTUMN;
    }
    public enum Weather{
        SUNNY, CLOUDY, RAINY, SNOWY;
    }
    
    private Seasons seasons;
    private Weather weather;
    private int     day;
    private int  random;

    public Calendar(){
        this.day = 1;
        this.seasons = Seasons.SPRING;
    }

    public int getDay(){
        return this.day;
    }

    public void inc(){
        /**
         * Control days skip
         */
        if(this.day % 120 == 0)
            this.day = 1 ;
        else
            this.day++;
            
        /**
         * Season verify that is going to change weather
         */
        verifySeason();
        randomWeather();

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
        if(this.day % 30 == 0){
            setSeason(Seasons.SUMMER);
        }
    }
    private void randomWeather(){

        this.random = (int)(Math.random() * 10 + 1);

        if(this.random > 0 || this.random < 6){
            setWeather(Weather.RAINY);
        }
        else if(this.random > 5 || this.random < 8){
            setWeather(Weather.SNOWY);
        }
        else if(this.random > 7 || this.random < 10){
            setWeather(Weather.CLOUDY);
        }
        else
            setWeather(Weather.SUNNY);
    }

    
}
