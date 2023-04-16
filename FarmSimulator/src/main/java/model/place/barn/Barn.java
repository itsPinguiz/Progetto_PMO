/********************
 * IMPORT AND PACKAGE
 *******************/

package model.place.barn;

import java.util.Iterator;

import model.actors.actions.PlaceActions;
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
    private final int MAX_BARN_LENGTH = 40;

    //constructor
    public Barn() throws NoItemFoundException, InventoryIsFullException, NoAnimalFoundException, NoProductFoundException{
        super.type = Places.BARN;
        super.actions = new PlaceActions(this);
        this.barnInventory = new Inventory(MAX_BARN_LENGTH);
        this.market = new Market();
    }
    
    //getters
    public Inventory getBarnInventory() {
        return this.barnInventory;
    }
    public Market getMarket() {
        return this.market;
    }

    //setters
    public void setItemBarnInventory(Item item) throws NoItemFoundException, InventoryIsFullException {
        this.barnInventory.addItem(item);
    }

    //update market and animals in the barn
    
    public void updateBarn() throws NoItemFoundException, InventoryIsFullException{
        //update market
        this.market.updateItemShop();
        //update animals
        Iterator<Item> iterator = this.barnInventory.getInventory().iterator();
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
