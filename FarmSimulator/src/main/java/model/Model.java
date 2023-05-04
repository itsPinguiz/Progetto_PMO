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
import model.item.animal.products.Products;
import model.item.plants.GamePlant;
import model.place.GameMap;
import model.place.Place;
import model.place.barn.Barn;
import model.place.land.LandAbstract;
import model.progress.GameBackupManager;

/**
 * Model class of the MVC pattern, contains all the data of the game
 */
public class Model implements Serializable{
    /**
     * Attributes
     */
    private Farmer farmer;
    private Landlord landlord;
    private Person selectedActor;
    private Calendar calendar;
    private GameMap map;
    private GameBackupManager backup;

    private Object selectedItem;
    private Inventory oldInventory;
    private Place oldPlace;

    /**
     * Constructor
     * Initializes the farmer, the landlord, the calendar, the map and the selected actor
     * @throws NoItemFoundException
     * @throws InventoryIsFullException
     * @throws NoAnimalFoundException
     * @throws NoProductFoundException
     * @throws CloneNotSupportedException
     */
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
        this.farmer.getInventory().addItem(new GamePlant(Plants.CARROT) {{
            setNumber(11);
        }});
        
        this.farmer.getInventory().addItem(new AnimalFactory().createChicken());
        
        this.farmer.getInventory().addItem(new Products(productsType.MEAT) {{
            setNumber(64);
        }});

        // add starting items to the barn
        map.getBarn().getBarnInventory().addItem(new ItemCreator().getRandomItem());
        map.getBarn().getBarnInventory().addItem(new GamePlant(Plants.CARROT));
    }

    /**
     * Initliazes the game backup
     */
    public void initializeGameBackup(){
        this.backup = new GameBackupManager(this);
    }

    
    /** 
     * Updates the world entities
     * @throws InventoryIsFullException
     * @throws NoItemFoundException
     * @throws CloneNotSupportedException
     * @throws PlaceNotAvailableException
     */
    public void update() throws InventoryIsFullException,
                                NoItemFoundException, 
                                CloneNotSupportedException, 
                                PlaceNotAvailableException {
        this.calendar.inc();

        ((ArrayList<LandAbstract>)(this.map.getLands())).forEach(place -> { LandAbstract land = (LandAbstract) place;
                                                      land.update();});

        this.getBarn().updateBarn();

        if (this.selectedActor.getPlace()!=null)
            this.selectedActor.getActions().enter(this.selectedActor.getPlace());
    }

    /**
     * Method to make the actionManager execute an action
     * @param action the action to execute
     * @throws Exception if the action is not executable
     */
    public void sendAction(Action action) throws Exception{
        ActionArguments<Place, Object, GameMap> arguments = new ActionArguments<>(getSelectedPerson().getPlace(), 
                                                                                                      getSelectedItem(), 
                                                                                                      getMap());
        this.selectedActor.getActions().executeAction(action,arguments);
    }

    /**
     * Method to get the Barn
     * @return Barn
     */
    public Barn getBarn(){
        return this.map.getBarn();
    }

    /**
     * Method to get the map
     * @return GameMap
     */
    public GameMap getMap(){
        return this.map;
    }

    /**
     * Method to get the lands
     * @return  the lands
     */
    public ArrayList<LandAbstract> getLands(){
        return map.getLands();
    }

    /**
     * Method to set the selected actor
     * @param person the person to set
     */
    public void setSelectedPerson(Person person){
        this.selectedActor = person;
    }

    /**
     * Method to get the selected actor's place
     * @return  the place of the selected actor
     */
    public Place getPersonPlace(){
        return this.selectedActor.getPlace();
    }

    /**
     * Method to get the selected actor
     * @return  the selected actor
     */
    public Person getSelectedPerson(){
        return this.selectedActor;
    }

    /**
     * Method to get both the possible roles
     * @return
     */
    public HashMap<Role,Person> getRoles(){
        return new HashMap<>(){{
            put(Role.FARMER, farmer);
            put(Role.LANDLORD, landlord);
        }};
    }

    /**
     * Method to set a new selected item
     * @param newItem the new selected item
     */
    public void setSelectedItem(Object newItem){
        this.selectedItem = newItem;
    }

    /**
     * Method to get the selected item
     * @return  the selected item
     */
    public Object getSelectedItem(){
        return this.selectedItem;
    }

    /**
     * Method to set the old inventory
     * @param oldInventory
     */
    public void setOldInventory(Inventory oldInventory){
        this.oldInventory = oldInventory;
    }

    /**
     * Method to get the old inventory
     * @return  the old inventory
     */
    public Inventory getOldInventory(){
        return this.oldInventory;
    }

    /**
     * Method to set the old place
     * @param oldPlace
     */
    public void setOldPlace(Place oldPlace){
        this.oldPlace = oldPlace;
    }

    /**
     * Method to get the old place
     * @return  the old place
     */
    public Place getOldPlace(){
        return this.oldPlace;
    }

    /**
     * Method to get the backupManager
     * @return  the backupManager
     */
    public GameBackupManager getBackup(){
        return this.backup;
    }
}
