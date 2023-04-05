/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn;

import Place.Places;
import Place.Barn.Market.Market;

import java.util.Iterator;

import Actors.Actions.PlaceActions;
import Exceptions.CustomExceptions.NoItemFoundException;
import Inventory.Inventory;
import Item.Animal.AnimalAbstract;
import Item.Interface.Item;
import Place.Place;

/************
 * BARN CLASS
 ***********/
public class Barn extends Place{

    //attributes
    private Inventory barnInventory;
    private Market market;
    private final int MAX_BARN_LENGTH = 40;

    //constructor
    public Barn() throws NoItemFoundException{
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

    //update market and animals in the barn
    
    public void updateBarn(){
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
