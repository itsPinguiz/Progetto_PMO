package Main;

import java.util.ArrayList;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Actors.Person.Person;
import Calendar.Calendar;
import Place.Place;
import Place.Barn.Barn;
import Place.Land.LandAbstract;

/*
The project consists in the implementation of a farm simulator.

Main functions:

The user will be able to take on the role of owner and farmer.
The owner will be able to buy and sell portions of land, as well as the necessary resources.
The farmer will have to work the portion of land to allow the growth of plants or the breeding of animals.
Each portion of land can host animals or plants.
A repository for storing tools, resources, and animals.
A shop from which to buy seeds, resources, animals or land.
Variation of the seasons that influence the weather.
*/

public class Game {
    private Farmer farmer;
    private Landlord landlord;
    private Person selectedActor;
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
        this.selectedActor = this.farmer;
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

    public void changePerson(){
        if (this.selectedActor == this.farmer){
            this.selectedActor = this.landlord;
        }else{
            this.selectedActor = this.farmer;
        }
    }

    public Place getPersonPlace(){
        return this.selectedActor.getPlace();
    }

    public Person getSelectedPerson(){
        return this.selectedActor;
    }
}
