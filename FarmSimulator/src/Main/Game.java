package Main;

import java.util.ArrayList;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Calendar.Calendar;
import Place.Place;
import Place.Barn.Barn;
import Place.Land.LandAbstract;

public class Game {
    private Farmer farmer;
    private Landlord landlord;
    private Calendar calendar;

    public Game(){
        this.farmer = new Farmer();
        this.landlord = new Landlord();
        this.calendar = Calendar.getInstance();
    
        GameData.map = new ArrayList<>(){
            {
                add(new ArrayList<>() {{add(new Barn());}}); // barn
                add(new ArrayList<>()); // lands
            }
        };
    }

    public static class GameData
    {
        public static ArrayList<ArrayList<Place>> map;

        public static int firstIndex; 
        public static int secondIndex; 
    }
    
    public void update(){
        this.calendar.inc();

        GameData.map.get(2).forEach(place -> { LandAbstract land = (LandAbstract) place;
                                                      land.update();});
    }
}
