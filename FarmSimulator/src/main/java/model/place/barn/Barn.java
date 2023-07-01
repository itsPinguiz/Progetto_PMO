/********************
 * IMPORT AND PACKAGE
 *******************/

package model.place.barn;

import java.util.Iterator;

import model.Constants;
import model.actors.actions.placeActions.PlaceActions;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.inventory.Inventory;
import model.item.Item;
import model.item.animal.AnimalAbstract;
import model.place.Place;
import model.place.Places;
import model.place.barn.market.Market;

/************
 * BARN CLASS
 ***********/
public class Barn extends Place{

    //attributes
    private Inventory barnInventory;
    private Market market;

    /**
     * Constructs a new Barn object.
     *
     * @throws NoItemFoundException         if no item is found
     * @throws InventoryIsFullException     if the inventory is full
     * @throws NoAnimalFoundException       if no animal is found
     * @throws NoProductFoundException      if no product is found
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public Barn() throws NoItemFoundException, InventoryIsFullException, NoAnimalFoundException, NoProductFoundException, CloneNotSupportedException{
        super.type = Places.BARN;
        super.actions = new PlaceActions(this);
        this.barnInventory = new Inventory(Constants.BARN_INVENTORY_MAX);
        this.market = new Market();
    }
    
    /**
     * Returns the barn inventory.
     *
     * @return the barn inventory
     */
    public Inventory getBarnInventory() {
        return this.barnInventory;
    }

    /**
     * Returns the market associated with the barn.
     *
     * @return the market associated with the barn
     */
    public Market getMarket() {
        return this.market;
    }

    /**
     * Adds an item to the barn inventory.
     *
     * @param item the item to add
     * @throws NoItemFoundException         if no item is found
     * @throws InventoryIsFullException     if the inventory is full
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public void setItemBarnInventory(Item item) throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException {
        this.barnInventory.addItem(item);
    }

    /**
     * Updates the barn inventory and animals.
     *
     * @throws NoItemFoundException         if no item is found
     * @throws InventoryIsFullException     if the inventory is full
     * @throws CloneNotSupportedException if cloning is not supported
     */    
    public void updateBarn() throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException{
        //update market
        this.market.updateItemShop();
        //update animals
        Iterator<Item> iterator = this.barnInventory.getItemInventory().iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if(item instanceof AnimalAbstract){
                ((AnimalAbstract)item).update();
                //if the animal is dead, remove it from the barn
                if(((AnimalAbstract) item).getHunger() >= 100 || ((AnimalAbstract) item).getStatus() <= 0){
                    iterator.remove();
                }
            }
        }
    }
}
