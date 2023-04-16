package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.actors.person.Farmer;
import model.actors.person.Landlord;
import model.actors.person.Person;
import model.calendar.Calendar;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.item.ItemCreator;
import model.item.ItemType.Plants;
import model.item.ItemType.productsType;
import model.item.animal.AnimalFactory;
import model.item.plants.species.Plant;
import model.item.products.Products;
import model.place.Place;
import model.place.barn.Barn;
import model.place.land.AnimalLand;
import model.place.land.LandAbstract;
import model.place.land.PlantLand;

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
    public Model() throws NoItemFoundException, InventoryIsFullException,NoAnimalFoundException,NoProductFoundException{
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
        Barn b = (Barn)(this.map.get(0).get(0));
        b.getMarket().addBarn(b);

        this.selectedActor = this.farmer; // default selected actor

        this.farmer.getInventory().addItem(new Plant(Plants.CARROT) {{
            setNumber(11);
        }});
        
        this.farmer.getInventory().addItem(new AnimalFactory().createChicken());

        for(int i = 0; i < 70; i++){
            b.getBarnInventory().addItem(new Products(productsType.MEAT));
        }

        b.getBarnInventory().addItem(new ItemCreator().getRandomItem());
        b.getBarnInventory().addItem(new Plant(Plants.CARROT));
    }

    public void update() throws InventoryIsFullException,NoItemFoundException, CloneNotSupportedException{
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
