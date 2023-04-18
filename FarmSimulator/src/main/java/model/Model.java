package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.actors.person.Farmer;
import model.actors.person.Landlord;
import model.actors.person.Person;
import model.actors.person.PersonAbstract.Role;
import model.calendar.Calendar;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.exceptions.CustomExceptions.PlaceNotAvailableException;
import model.inventory.Inventory;
import model.item.ItemCreator;
import model.item.ItemType.Plants;
import model.item.ItemType.productsType;
import model.item.animal.AnimalFactory;
import model.item.plants.species.Plant;
import model.item.products.Products;
import model.place.Place;
import model.place.Places;
import model.place.barn.Barn;
import model.place.land.LandAbstract;
import model.progress.GameBackup;

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
    private HashMap<Places,Object> map;
    private GameBackup backup;
    private Object selectedItem;
    private Inventory oldInventory;
    private Place oldPlace;

    // constructor
    public Model() throws NoItemFoundException, InventoryIsFullException,NoAnimalFoundException,NoProductFoundException, CloneNotSupportedException{
        //attributes initialization
        this.farmer = new Farmer();
        this.landlord = new Landlord();
        this.calendar = Calendar.getInstance();
        this.oldInventory = null;
        this.selectedItem = null;
        this.oldPlace = null;
        ArrayList<LandAbstract> lands = new ArrayList<>();
    
        map = new HashMap<>(){
            {
                put(Places.BARN, new Barn());
                put(Places.PLANT_LAND, lands);
                put(Places.ANIMAL_LAND, lands);
            }
        };
        
        Barn b = (Barn)(this.map.get(Places.BARN));
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

    @SuppressWarnings("unchecked")
    public void update() throws InventoryIsFullException,NoItemFoundException, CloneNotSupportedException, PlaceNotAvailableException{
        /*
         * Method to update the world entities
         */
        this.calendar.inc();

        ((ArrayList<LandAbstract>)(this.map.get(Places.PLANT_LAND))).forEach(place -> { LandAbstract land = (LandAbstract) place;
                                                      land.update();});

        this.getBarn().updateBarn();

        if (this.selectedActor.getPlace()!=null)
            this.selectedActor.getActions().enter(this.selectedActor.getPlace());
    }

    public Barn getBarn(){
        /*
         * Method to get the barn
         */
        return (Barn)this.map.get(Places.BARN);
    }

    public HashMap<Places,Object> getMap(){
        /*
         * Method to get the map
         */
        return this.map;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<LandAbstract> getLands(){
        /*
         * Method to get the lands
         */
        return (ArrayList<LandAbstract>)this.map.get(Places.PLANT_LAND);
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

    public HashMap<Role,Person> getPlayer(){
        /*
         * Method to get the persons
         */
        return new HashMap<>(){{
            put(Role.FARMER, farmer);
            put(Role.LANDLORD, landlord);
        }};
    }

    public Calendar getCalendar(){
        /*
         * Method to get the calendar
         */
        return this.calendar;
    }

    public void setSelectedItem(Object o){
        /*
         * Method to set the selected item
         */
        this.selectedItem = o;
    }

    public Object getSelectedItem(){
        /*
         * Method to get the selected item
         */
        return this.selectedItem;
    }

    public void setOldInventory(Inventory i){
        /*
         * Method to set the old inventory
         */
        this.oldInventory = i;
    }

    public Inventory getOldInventory(){
        /*
         * Method to get the old inventory
         */
        return this.oldInventory;
    }

    public void setOldPlace(Place p){
        /*
         * Method to set the old place
         */
        this.oldPlace = p;
    }

    public Place getOldPlace(){
        /*
         * Method to get the old place
         */
        return this.oldPlace;
    }

    public GameBackup getBackup(){
        /*
         * Method to get the backup
         */
        return this.backup;
    }
}
