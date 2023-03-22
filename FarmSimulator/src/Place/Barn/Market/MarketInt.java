/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn.Market;

import java.util.ArrayList;

import Item.Interface.Item;

/******************
 * MARKET INTERFACE
 *****************/
public interface MarketInt {
    ArrayList<Item> getItem();
    void removeItem(int itemIndex);
}
