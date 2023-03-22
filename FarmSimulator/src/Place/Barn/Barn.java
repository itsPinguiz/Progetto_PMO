/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn;

import java.util.ArrayList;

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
}
