package Calendar;

public class Season implements SeasonInt{

    private String season;
    private String weather;

    public Season(){
        this.season  = "Spring";
        this.weather = "Sunny";
    }

    public String getWeather(){
        return this.weather;
    }
    public String getSeason(){
        return this.season;
    }
    public void setWeather(String weather){
       this.weather = weather; 
    }
    public void setSeason(String season){
        this.season = season;
    }
}
