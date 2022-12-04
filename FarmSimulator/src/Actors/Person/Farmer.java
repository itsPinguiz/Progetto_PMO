package Actors.Person;

import Tools.ToolCreator;
import Tools.ConcreteTool.Interface.Tool;

/**
 * Farmer person implementation
 */
public class Farmer implements Person{
    
    /**
     * Fields
     */
    private final int MAX_INVENTORY_SIZE = 4;
    private Object actualPlace;
    private Tool[] inventory;
    private ToolCreator creator;

    /**
     * Methods
     */
    public Farmer(){
        this.actualPlace = null;
        this.inventory = new Tool[MAX_INVENTORY_SIZE];
        this.creator = new ToolCreator();
        this.inventory = creator.getStandardInventory();
    }

    public Object getPlace(){
        return actualPlace;
    }

    public void setPlace(Object newPlace){
        this.actualPlace = newPlace;
    }

    public void getInventory(){
        for(int i = 0; i < inventory.length; i++){
            if(this.inventory[i] != null){
                System.out.println("\n[" + (i + 1) + "][ITEM]: " + inventory[i].getType() +
                             " [STATUS]: " + inventory[i].getStatus() + " [PRICE]: " + inventory[i].getPrice());
            }
            else{
                System.out.println("\n["+ (i + 1) +"][ITEM]: Empty");
            }
        }
    }

    public void removeItem(int i){
        if(this.inventory[i] == null){
            System.out.println("\nThere's nothing to remove from slot ["+ (i + 1) +"].");
        }
        else{
            System.out.println("\n" + this.inventory[i].getType() + " has been removed from your inventory.");
            this.inventory[i] = null;
            
        }
    }

    public void addItem(Tool newTool, int i){
        if(this.inventory[i] == null){
            this.inventory[i] = newTool;
            System.out.println("\n" + this.inventory[i].getType() + " has been added in slot [" + (i+1) + "] of your inventory.");
        }
        else{
            System.out.println("\nSlot [" + ( i+1 ) + "] is occupied! Try with an other slot. ");
        }
    }

}
