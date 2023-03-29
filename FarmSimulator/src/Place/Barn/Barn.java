/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn;

import java.util.ArrayList;
import Place.Places;
import Actors.Actions.PlaceActions;
import Animal.AnimalAbstract;
import Item.Interface.Item;
import Place.Place;

/************
 * BARN CLASS
 ***********/
public class Barn extends Place{
    private PlaceActions actionBarn;
    private ArrayList<AnimalAbstract> stall;
    private ArrayList<Item> items;
    //istanza di market per vendere e comprare
    public Barn(){
        super.type = Places.BARN;
        this.actionBarn = new PlaceActions(this);
        this.stall = new ArrayList<>();
        this.items = new ArrayList<>();
    }
}
