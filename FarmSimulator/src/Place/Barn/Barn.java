/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn;

import java.util.ArrayList;
import Place.Places;
import Place.Barn.Market.Market;
import Actors.Actions.PlaceActions;
import Item.Animal.AnimalAbstract;
import Calendar.Calendar;
import Item.Interface.Item;
import Place.Place;

/************
 * BARN CLASS
 ***********/
public class Barn extends Place{
    private PlaceActions actionBarn;
    private ArrayList<AnimalAbstract> stall;
    private ArrayList<Item> items;
    private Market market;

    //istanza di market per vendere e comprare
    public Barn(){
        super.type = Places.BARN;
        this.actionBarn = new PlaceActions(this);
        this.stall = new ArrayList<>();
        this.items = new ArrayList<>();
        this.market = new Market();
    }

    public ArrayList<AnimalAbstract> getStall() {
        return stall;
    }

    public void addAnimal(AnimalAbstract animal){
        this.stall.add(animal);
    }

    public void removeAnimal(AnimalAbstract animal){
        this.stall.remove(animal);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public PlaceActions getActionBarn() {
        return actionBarn;
    }

    public Market getMarket() {
        return market;
    }
    
    public void updateMarket(){
        this.market.upgradeItemShop(Calendar.getInstance().getDay());
    }
}
