package GUI;

import java.io.Serializable;
import java.util.ArrayList;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Actors.Person.Person;
import Calendar.Calendar;
import Exceptions.CustomExceptions.NoItemFoundException;
import Item.ItemCreator;
import Item.ItemType;
import Item.Interface.Item;
import Item.ItemType.Plants;
import Item.Plants.species.Plant;
import Item.Products.Products;
import Place.Place;
import Place.Barn.Barn;
import Place.Land.AnimalLand;
import Place.Land.LandAbstract;
import Place.Land.PlantLand;

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

public class Model implements Serializable{
    // attributes\
    private Farmer farmer;
    private Landlord landlord;
    private Person selectedActor;
    private Calendar calendar;
    private ArrayList<ArrayList<Place>> map;

    // constructor
    public Model() throws NoItemFoundException{
        //attributes initialization
        this.farmer = new Farmer();
        this.landlord = new Landlord();
        this.calendar = Calendar.getInstance();
    
        map = new ArrayList<>(){
            {
                add(new ArrayList<>() {{add(new Barn());}}); // barn
                add(new ArrayList<>(){ // lands
                    {
                        add(new PlantLand());
                        add(new PlantLand());
                        add(new AnimalLand());
                        add(new AnimalLand());
                    }
                }); 
            }
        };

        this.selectedActor = this.farmer; // default selected actor

         // TODO: remove this
        this.farmer.getInventory().addItem(new Plant(Plants.CARROT));
        Barn b = (Barn)(this.map.get(0).get(0));

        for (int i = 0; i < 70; i++) {
            b.getBarnInventory().addItem(new Products(ItemType.productsType.MEAT));
        }

        b.getBarnInventory().addItem(new ItemCreator().getRandomItem());
        b.getBarnInventory().addItem(new Plant(Plants.CARROT));
    }

    public void update(){
        /*
         * Method to update the world entities
         */
        this.calendar.inc();

        this.map.get(1).forEach(place -> { LandAbstract land = (LandAbstract) place;
                                                      land.update();});

        Barn b = (Barn)(this.map.get(0).get(0));
        b.updateBarn();
    }

    public ArrayList<ArrayList<Place>> getMap(){
        /*
         * Method to get the map
         */
        return this.map;
    }

    public ArrayList<Place> getLands(){
        /*
         * Method to get the lands
         */
        return this.map.get(1);
    }

    public void setSelectedPerson(Person p){
        /*
         * Method to set the selected person 
         */
        this.selectedActor = p;
    }

    public Place getPersonPlace(){
        /*
         * Method to get the place of the selected person 
         */
        return this.selectedActor.getPlace();
    }

    public Person getSelectedPerson(){
        /*
         * Method to get the selected person 
         */
        return this.selectedActor;
    }

    public Person[] getPersons(){
        /*
         * Method to get the persons
         */
        return new Person[]{this.farmer, this.landlord};
    }

    public Calendar getCalendar(){
        /*
         * Method to get the calendar
         */
        return this.calendar;
    }
}
