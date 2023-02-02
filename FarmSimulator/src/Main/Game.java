package Main;

import java.util.ArrayList;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Place.Place;
import Place.Barn.Barn;

public class Game {
    private Farmer farmer;
    private Landlord landlord;

    public static class GameData
    {
        public static ArrayList<ArrayList<Place>> map;

        public static int firstIndex; 
        public static int secondIndex; 
    }
    

    public Game(){
        this.farmer = new Farmer();
        this.landlord = new Landlord();
    
        GameData.map = new ArrayList<>(){
            {
                add(new ArrayList<>() {{add(new Barn());}}); // barn
                add(new ArrayList<>()); // lands
            }
        };
    }
}
