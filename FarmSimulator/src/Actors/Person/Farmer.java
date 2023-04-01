/********************
 * IMPORT AND PACKAGE
 *******************/

package Actors.Person;

import Actors.Actions.PlayerActions;
import Inventory.Inventory;
import Item.ItemCreator;

/******************************
 * FARMER PERSON IMPLEMENTATION
 *****************************/
public class Farmer extends PersonAbstract{
    
    /**
     * Fields
     */
    private final int MAX_INVENTORY_SIZE = 10;
    private Inventory inventory;
    private ItemCreator creator;

    /**
     * Methods
     */
    public Farmer(){
        super.personAction = new PlayerActions(this);
        super.place = null;
        this.inventory = new Inventory(MAX_INVENTORY_SIZE);
        this.creator = new ItemCreator();
        this.inventory.setInventory(creator.getWoodSet());
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Farmer";
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    

}
