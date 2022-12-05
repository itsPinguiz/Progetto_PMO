package Calendar;

public class Calendar implements CalendarInt {
    
    int day;
    Season season;

    public Calendar(){
        this.day = 90;
        this.season = new Season();
    }

    public int getDay(){
        return this.day;
    }

    public void inc(){
    }
}
