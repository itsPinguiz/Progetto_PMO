package Actors.Person;

import java.util.ArrayList;
import Actors.Actions.FarmerActions;
import Item.ItemCreator;
import Item.Interface.Item;

/**
 * Farmer person implementation
 */
public class Farmer implements Person{
    
    /**
     * Fields
     */
    private final int MAX_INVENTORY_SIZE = 4;
    private Object actualPlace;
    private ArrayList<Item> inventory;
    private ItemCreator creator;
    private FarmerActions action;

    /**
     * Methods
     */
    public Farmer(){
        this.actualPlace = null;
        this.inventory = new ArrayList<Item>(MAX_INVENTORY_SIZE);
        this.creator = new ItemCreator();
        this.inventory = creator.getWoodSet();
        this.action = new FarmerActions(this);
    }

    public FarmerActions getActions(){
        return this.action;
    }

    public void setActions(FarmerActions newAction){
        this.action = newAction;
    }

    public Object getPlace(){
        return this.actualPlace;
    }

    public void setPlace(Object newPlace){
        this.actualPlace = newPlace;
    }

    public ArrayList<Item> getInventory(){
       return this.inventory;
    }

    public void removeItem(int i){
            System.out.println("\n" + this.inventory.get(i).getType() + " has been removed from your inventory.");
            this.inventory.remove(i);
    }

    public void addItem(Item newTool){
        if(this.inventory.size() < MAX_INVENTORY_SIZE){
            this.inventory.add(newTool);
            System.out.println("\n" + newTool.getType() +
                               " has been added in your inventory.");
        }
        else{
            System.out.println("\nThere's not enough space in your inventory, drop something to make space.");
        }
    }

}
