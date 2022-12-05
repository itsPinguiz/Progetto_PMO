package Calendar;

import Calendar.Season.Season;

public class Calendar implements CalendarInt {
    
    private int day;
    private Season season;

    public Calendar(Season season){
        this.day = 30;
        this.season = season;
    }

    public int getDay(){
        return this.day;
    }

    public void inc(){
        if(this.day == 359)
            this.day = 0 ;
        else
            this.day++;
        
        verifySeason();
    }

    public String getSeason(){
        return this.season.getSeason();
    }

    private void verifySeason(){
        if(this.day == 120){
            this.season.setSeason("Summer");
        }
        else if(this.day == 210){
            this.season.setSeason("Autumn");
        }
        else if(this.day == 300){
            this.season.setSeason("Winter");
        }
        else if(this.day == 30){
            this.season.setSeason("Spring");
        }
    }
}
