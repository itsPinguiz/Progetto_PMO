/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn.Market;

import java.io.Serializable;
import java.util.ArrayList;

import Item.Interface.Item;

/******************
 * MARKET INTERFACE
 *****************/
public interface MarketInt extends Serializable{
    ArrayList<Item> getItem();
}
