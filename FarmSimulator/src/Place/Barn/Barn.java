/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn;

import Place.Places;
import Place.Barn.Market.Market;
import Actors.Actions.PlaceActions;
import Inventory.Inventory;
import Item.Animal.AnimalAbstract;
import Item.Interface.Item;
import Place.Place;

/************
 * BARN CLASS
 ***********/
public class Barn extends Place{
    private PlaceActions actionBarn;
    private Inventory barnInventory;
    private Market market;
    private final int MAX_BARN_LENGTH = 40;

    //istanza di market per vendere e comprare
    public Barn(){
        super.type = Places.BARN;
        this.actionBarn = new PlaceActions(this);
        this.barnInventory = new Inventory(MAX_BARN_LENGTH);
        this.market = new Market();
    }

    public Inventory getBarnInventory() {
        return this.barnInventory;
    }

    public PlaceActions getActionBarn() {
        return this.actionBarn;
    }

    public Market getMarket() {
        return this.market;
    }

    public void updateBarn(){
        this.market.updateItemShop();

        for (Item item : this.barnInventory.getInventory()) {
            if(item.getType() instanceof AnimalAbstract ){
                ((AnimalAbstract) item.getType()).update();
            }
        }
    }
}
