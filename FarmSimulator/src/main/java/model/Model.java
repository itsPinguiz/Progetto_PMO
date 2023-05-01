package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.actors.actions.ActionArguments;
import model.actors.actions.ActionsManager.Action;
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
import model.item.plants.products.Products;
import model.item.plants.species.Plant;
import model.place.GameMap;
import model.place.Place;
import model.place.barn.Barn;
import model.place.land.LandAbstract;
import model.progress.GameBackup;

public class Model implements Serializable{
    // attributes\
    private Farmer farmer;
    private Landlord landlord;
    private Person selectedActor;
    private Calendar calendar;
    private GameMap map;
    private GameBackup backup;
    private Object selectedItem;
    private Inventory oldInventory;
    private Place oldPlace;

    // constructor
    public Model() throws NoItemFoundException, 
                          InventoryIsFullException,
                          NoAnimalFoundException,
                          NoProductFoundException, 
                          CloneNotSupportedException{

        //attributes initialization
        this.farmer = new Farmer();
        this.landlord = new Landlord();
        this.calendar = Calendar.getInstance();
        this.map = new GameMap();
        this.oldInventory = null;
        this.selectedItem = null;
        this.oldPlace = null;

        this.selectedActor = this.farmer; // default selected actor
        
        // add starting items to the farmer
        this.farmer.getInventory().addItem(new Plant(Plants.CARROT) {{
            setNumber(11);
        }});
        
        this.farmer.getInventory().addItem(new AnimalFactory().createChicken());
        
        this.farmer.getInventory().addItem(new Products(productsType.MEAT) {{
            setNumber(70);
        }});

        // add starting items to the barn
        map.getBarn().getBarnInventory().addItem(new ItemCreator().getRandomItem());
        map.getBarn().getBarnInventory().addItem(new Plant(Plants.CARROT));
    }

    public void initializeGameBackup(){
        /*
         * Method to initialize the game backup
         */
        this.backup = new GameBackup(this);
    }

    public void update() throws InventoryIsFullException,
                                NoItemFoundException, 
                                CloneNotSupportedException, 
                                PlaceNotAvailableException {
        /*
         * Method to update the world entities
         */
        this.calendar.inc();

        ((ArrayList<LandAbstract>)(this.map.getLands())).forEach(place -> { LandAbstract land = (LandAbstract) place;
                                                      land.update();});

        this.getBarn().updateBarn();

        if (this.selectedActor.getPlace()!=null)
            this.selectedActor.getActions().enter(this.selectedActor.getPlace());
    }

    public void sendAction(Action action) throws Exception{
        ActionArguments<Place, Object, GameMap> arguments = new ActionArguments<>(getSelectedPerson().getPlace(), 
                                                                                                 getSelectedItem(), 
                                                                                                 getMap());
        this.selectedActor.getActions().executeAction(action,arguments);
    }

    public Barn getBarn(){
        /*
         * Method to get the barn
         */
        return this.map.getBarn();
    }

    public GameMap getMap(){
        /*
         * Method to get the map
         */
        return this.map;
    }

    public ArrayList<LandAbstract> getLands(){
        /*
         * Method to get the lands
         */
        return map.getLands();
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
